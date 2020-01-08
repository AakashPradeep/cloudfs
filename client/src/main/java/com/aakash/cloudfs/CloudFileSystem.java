package com.aakash.cloudfs;

import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus;
import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap;
import com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo;
import com.aakash.cloudfs1.mapper.CfsPathToVendorPathMapper;
import com.aakash.cloudfs1.mapper.FileStatusMapper;
import com.google.common.base.Preconditions;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Progressable;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CloudFileSystem extends FileSystem {
    public static final String SCHEME = "cfs";
    private String namespace;
    private URI uri;
    private HAMetadataClientServiceProvider metadataClientProvider;
    private Configuration configuration;
    private FileSystem vendorFileSystem;
    private NamespaceInfo namespaceInfo;
    private Path workingDir;


    public CloudFileSystem(String namespace) {
        this.namespace = namespace;
    }

    public CloudFileSystem() {
    }

    static void checkAccessPermissions(FileStatus stat, FsAction mode)
            throws IOException {
        FsPermission perm = stat.getPermission();
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());
        if (user.equals(stat.getOwner())) {
            if (perm.getUserAction().implies(mode)) {
                return;
            }
        } else if (groups.contains(stat.getGroup())) {
            if (perm.getGroupAction().implies(mode)) {
                return;
            }
        } else {
            if (perm.getOtherAction().implies(mode)) {
                return;
            }
        }
        throw new AccessControlException(String.format(
                "Permission denied: user=%s, path=\"%s\":%s:%s:%s%s", user, stat.getPath(),
                stat.getOwner(), stat.getGroup(), stat.isDirectory() ? "d" : "-", perm));
    }

    @Override
    public void initialize(URI uri, Configuration conf) throws IOException {
        super.initialize(uri, conf);
        this.uri = uri;
        this.configuration = conf;
        Preconditions.checkArgument(uri.getScheme().equals(getScheme()), "scheme" + this.uri.getScheme() + " is not " + getScheme());
        Optional<String> optionalNamespace = Optional.ofNullable(uri.getAuthority());
        this.namespace = optionalNamespace.orElse(conf.get(Constants.FS_CFS_DEFAULT_NAMESPACE));
        Preconditions.checkArgument(org.apache.commons.lang.StringUtils.isNotEmpty(this.namespace), "did not find namespace please provide as uri authority or set config " + Constants.FS_CFS_DEFAULT_NAMESPACE);

        this.workingDir = new Path("/", this.namespace);
        this.metadataClientProvider = getMetadataClientProvider(conf);
        MetadataClientService clientService = this.metadataClientProvider.provide(this.namespace, this.configuration);
        this.namespaceInfo = clientService.getNamespaceInfo(this.namespace);
        try {
            this.vendorFileSystem = FileSystem.get(new URI(this.namespaceInfo.getUri()), this.configuration);
        } catch (URISyntaxException e) {
            throw new IOException("error while getting vendor's file system's uri", e);
        }

        if (this.vendorFileSystem == null) {
            throw new IOException("not able to initialize the vendor file system for namespace:" + this.namespace);
        }

    }

    private HAMetadataClientServiceProvider getMetadataClientProvider(Configuration conf) throws IOException {
        Class<HAMetadataClientServiceProvider> clientServiceProviderClass = (Class<HAMetadataClientServiceProvider>)
                conf.getClass(Constants.FS_CFS_HA_METADATA_CLIENT_SERVICE_PROVIDER_CLASS,
                        HAMetadataClientServiceProvider.SimpleMetadataClientServiceProvider.class);
        try {
            return clientServiceProviderClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void setPermission(Path p, FsPermission permission) throws IOException {
        super.setPermission(p, permission);
    }

    @Override
    public String getScheme() {
        return SCHEME;
    }

    public java.net.URI getUri() {
        return this.uri;
    }

    public FSDataInputStream open(Path f, int bufferSize) throws IOException {
        MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());
        CFSFileStatus cfsFileStatus = clientService.getFileStatus(this.namespace, f, user, groups);
        if (cfsFileStatus == null) {
            throw new FileNotFoundException(f + " not found");
        }
        if (!cfsFileStatus.getAttribute().getIsFile()) {
            throw new IOException(f + " is not a file");
        }
        checkAccessPermissions(new FileStatusMapper().apply(cfsFileStatus), FsAction.READ_EXECUTE);
        if (cfsFileStatus.getAttribute().getFileSize() == 0) {
            return new FSDataInputStream(new ByteArrayInputStream(new byte[0]));
        }

        if (org.apache.commons.lang.StringUtils.isEmpty(cfsFileStatus.getVendorPath())) {
            throw new IOException("did not find vendor specific path from metadata server");
        }

        Path vendorPath = new Path(cfsFileStatus.getVendorPath());
        if (this.vendorFileSystem == null) {
            throw new IOException(" file system not initialized for namespace:" + this.namespace);
        }

        return this.vendorFileSystem.open(vendorPath, bufferSize);
    }

    public FSDataOutputStream create(Path f, FsPermission permission, boolean overwrite, int bufferSize, short replication, long blockSize, Progressable progress) throws IOException {
        final MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());

        try {
            FileStatus fileStatus = getFileStatusInternalUsingClientService(f, clientService, user, groups);
            if (fileStatus != null) {
                throw new FileAlreadyExistsException("file already exist with path:" + f);
            }
        } catch (FileNotFoundException fe){
            //expected ignore
        }

        Optional<? extends Class<?>> mapperKlass = Optional.ofNullable(configuration.getClassByNameOrNull(Constants.FS_CFS_PATH_MAPPER_CLASS));
        CfsPathToVendorPathMapper pathMapper = getPathMapper(mapperKlass);
        Path vendorPath = pathMapper.map(this.namespace, f, this.namespaceInfo.getBucketName(), this.namespaceInfo.getUri());

        if (!clientService.createZeroByteFile(this.namespace, f, vendorPath, user, groups, replication, blockSize)) {
            throw new IOException("cannot create the file with path:" + f);
        }

        final FSDataOutputStream vendorOutputStream = this.vendorFileSystem.create(vendorPath, permission, overwrite, bufferSize, replication, blockSize, progress);
        return new FSDataOutputStream(new CfsOutputStream(vendorOutputStream, blockSize, replication, clientService,
                f, vendorPath, user, groups, this.namespace, this.vendorFileSystem));
    }

    private CfsPathToVendorPathMapper getPathMapper(Optional<? extends Class<?>> mapperKlass) throws IOException {
        if (!mapperKlass.isPresent()) {
            return new CfsPathToVendorPathMapper.IdentityPathMapper();
        } else {
            try {
                return (CfsPathToVendorPathMapper) mapperKlass.get().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IOException("exception while instantialing the path mapper clss:" + mapperKlass, e);
            }
        }
    }

    public FSDataOutputStream append(Path f, int bufferSize, Progressable progress) throws IOException {
        throw new UnsupportedOperationException(getClass().getSimpleName()
                + " doesn't support append");
    }

    public boolean rename(Path src, Path dst) throws IOException {

        if (src.equals(dst)) {
            return true;
        }

        if (dst.toString().startsWith(src.toString())) {
            throw new IOException("src cannot be renamed to its own child path");
        }

        MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());

        Optional.ofNullable(getFileStatusInternalUsingClientService(src, clientService, user, groups)).orElseThrow(() ->
                new FileNotFoundException("not found with src path:" + src));

        FileStatus dstParentFileStatus = Optional.ofNullable(getFileStatusInternalUsingClientService(dst.getParent(), clientService, user, groups)).orElseThrow(() ->
                new FileNotFoundException("not found rename dst path:" + dst.getParent()));

        if (!dstParentFileStatus.isDirectory()) {
            throw new IOException("rename dst parent with path:" + dst.getParent() + " is not a directory");
        }

        return clientService.rename(this.namespace, src, dst, user, groups);
    }

    public boolean delete(Path f, boolean recursive) throws IOException {
        final MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());

        Optional.ofNullable(getFileStatusInternalUsingClientService(f, clientService, user, groups)).orElseThrow(() ->
                new FileNotFoundException("file not found with path:" + f));

        return clientService.delete(this.namespace, f, recursive, user, groups);
    }

    public FileStatus[] listStatus(Path f) throws IOException {
        MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());
        CFSFileStatusMap fileStatusMap = clientService.listStatus(this.namespace, f, user, groups);
        if (fileStatusMap != null) {
            if (fileStatusMap.hasErrorMsg()) {
                throw new IOException(fileStatusMap.getErrorMsg().getMsg());
            }

            if (fileStatusMap.getCfsPathToFileStatusMapCount() == 0) {
                return new FileStatus[0];
            }

            Map<String, CFSFileStatus> cfsPathToFileStatusMapMap = fileStatusMap.getCfsPathToFileStatusMapMap();
            FileStatus[] result = new FileStatus[cfsPathToFileStatusMapMap.size()];
            final FileStatusMapper fileStatusMapper = new FileStatusMapper();
            return cfsPathToFileStatusMapMap.values().stream()
                    .map(cfs -> fileStatusMapper.apply(cfs))
                    .collect(Collectors.toSet())
                    .toArray(result);
        }
        return new FileStatus[0];
    }

    public Path getWorkingDirectory() {
        return this.workingDir;
    }

    public void setWorkingDirectory(Path new_dir) {
        Preconditions.checkNotNull(new_dir);
        this.workingDir = new_dir;
    }

    public boolean mkdirs(Path f, FsPermission permission) throws IOException {
        Optional<? extends Class<?>> mapperKlass = Optional.ofNullable(configuration.getClassByNameOrNull(Constants.FS_CFS_PATH_MAPPER_CLASS));
        CfsPathToVendorPathMapper pathMapper = getPathMapper(mapperKlass);
        Path vendorPath = pathMapper.map(this.namespace, f, this.namespaceInfo.getBucketName(), this.namespaceInfo.getUri());
        this.vendorFileSystem.mkdirs(vendorPath,permission);
        MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());
        return clientService.mkdirs(namespace, f, permission, user, groups);
    }

    public FileStatus getFileStatus(Path f) throws IOException {
        MetadataClientService clientService = this.metadataClientProvider.provide(namespace, this.configuration);
        UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
        String user = ugi.getShortUserName();
        List<String> groups = Arrays.asList(ugi.getGroupNames());
        return getFileStatusInternalUsingClientService(f, clientService, user, groups);
    }

    private FileStatus getFileStatusInternalUsingClientService(Path f, MetadataClientService clientService, String user1, List<String> groups1) throws IOException {

        CFSFileStatus cfsFileStatus = clientService.getFileStatus(this.namespace, f, user1, groups1);
        if (cfsFileStatus == null) {
            throw new FileNotFoundException(f + " not found");
        }
        return new FileStatusMapper().apply(cfsFileStatus);
    }
}
