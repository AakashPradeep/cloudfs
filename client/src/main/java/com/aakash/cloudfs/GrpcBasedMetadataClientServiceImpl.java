package com.aakash.cloudfs;

import com.aakash.cloudfs.bean.HostWithPort;
import com.aakash.cloudfs.protocol.proto.generated.stubs.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * A grpc client impl to communicate with GRPC based Metadata server.
 */
//intentionally it is package level so that cannot be called from any other package.
class GrpcBasedMetadataClientServiceImpl implements MetadataClientService {

    private final CloudFSServiceGrpc.CloudFSServiceBlockingStub blockingStub;


    public GrpcBasedMetadataClientServiceImpl(HostWithPort hostWithPort) {
        this(ManagedChannelBuilder.forAddress(hostWithPort.getHostname(), hostWithPort.getPort()));
    }

    public GrpcBasedMetadataClientServiceImpl(ManagedChannelBuilder<?> channelBuilder) {
        final ManagedChannel channel = channelBuilder.build();
        this.blockingStub = CloudFSServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public NamespaceInfo registerNewNamespace(String user, String group, String namespace, String vendorURI, String bucketname, Map<String, String> additionalInfo) throws IOException {
        NamespaceInfoReq namespaceInfoReq = NamespaceInfoReq.newBuilder().setOwner(user).setGroup(group)
                .setName(NamespaceName.newBuilder().setName(namespace).build())
                .setBucketName(bucketname)
                .setVendorUri(vendorURI)
                .putAllAdditionalInfo(additionalInfo).build();
        NamespaceInfo namespaceInfo = this.blockingStub.registerNameSpace(namespaceInfoReq);
        if(namespaceInfo.hasErrorMsg()){
            throw new IOException("Error While creating namesapce:" + namespace, new Exception(namespaceInfo.getErrorMsg().getMsg()));
        }
        return namespaceInfo;
    }

    @Override
    public NamespaceInfo getNamespaceInfo(String namspace) throws IOException {
        NamespaceName namespaceName = NamespaceName.newBuilder().setName(namspace).build();
        NamespaceInfo namespaceInfo = this.blockingStub.getNamespaceInfo(namespaceName);
        if (namespaceInfo.hasErrorMsg()) {
            throw new IOException(namespaceInfo.getErrorMsg().getMsg());
        }
        return namespaceInfo;
    }

    @Override
    public boolean mkdirs(String namespace, Path f, FsPermission permission, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        final DirReq dirReq = DirReq.newBuilder().setFsPath(fsPath).setPermission(permission.toShort()).build();
        final ResultMsg resultMsg = this.blockingStub.mkdirs(dirReq);
        if (!resultMsg.getSuccess()) {
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        return resultMsg.getSuccess();
    }

    @Override
    public CFSFileStatus getFileStatus(String namespace, Path f, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        final CFSFileStatus fileStatus = this.blockingStub.getFileStatus(fsPath);
        if (fileStatus.hasErrorMsg()) {
            throw new IOException(fileStatus.getErrorMsg().getMsg());
        }
        return fileStatus;
    }

    @Override
    public CFSFileStatusMap listStatus(String namespace, Path f, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        final CFSFileStatusMap fileStatusMap = this.blockingStub.getFileStatusMap(fsPath);
        if (fileStatusMap.hasErrorMsg()) {
            throw new IOException(fileStatusMap.getErrorMsg().getMsg());
        }
        return fileStatusMap;
    }

    public boolean exists(String namespace, Path f, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        ExistMsg existMsg = this.blockingStub.exists(fsPath);
        if (existMsg.hasErrorMsg()) {
            throw new IOException(existMsg.getErrorMsg().getMsg());
        }
        return existMsg.getExists();
    }

    @Override
    public boolean createZeroByteFile(String namespace, Path f, Path vendorPath, String owner, List<String> groups, short replication, long blockSize) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        CreateFileReq createFileReq = CreateFileReq.newBuilder()
                .setBlockSize(blockSize)
                .setFileSize(0)
                .setReplication(replication)
                .setVendorPath(vendorPath.toString())
                .setFsPath(fsPath).build();
        ResultMsg resultMsg = this.blockingStub.createZeroByteFile(createFileReq);
        if (resultMsg.hasErrorMsg()) {
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        return resultMsg.getSuccess();
    }

    public boolean createOnUploadCompletion(String namespace, Path f, Path vendorPath, String owner, List<String> groups, short replication, long blockSize, long fileSize) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        CreateFileReq createFileReq = CreateFileReq.newBuilder()
                .setBlockSize(blockSize)
                .setReplication(replication)
                .setFileSize(fileSize)
                .setVendorPath(vendorPath.toString())
                .setFsPath(fsPath).build();
        ResultMsg resultMsg = this.blockingStub.createOnUploadCompletion(createFileReq);
        if (resultMsg.hasErrorMsg()) {
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        return resultMsg.getSuccess();
    }


    @Override
    public boolean delete(String namespace, Path f, boolean recursive, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        DeleteFSReq deleteFSReq = DeleteFSReq.newBuilder().setFsPathReq(fsPath).setRecursive(recursive).build();
        ResultMsg resultMsg = this.blockingStub.delete(deleteFSReq);
        if (resultMsg.hasErrorMsg()) {
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        return resultMsg.getSuccess();
    }

    @Override
    public boolean rename(String namespace, Path srcPath, Path dstPath, String owner, List<String> groups) throws IOException {
        RenameFSPath renameFSPath = RenameFSPath.newBuilder().setNamespace(namespace).setOwner(owner).addAllGroup(groups).setSrcPath(srcPath.toString())
                .setDstPath(dstPath.toString()).build();
        RenameMsg renameMsg = this.blockingStub.rename(renameFSPath);
        if (renameMsg.hasErrorMsg()) {
            throw new IOException(renameMsg.getErrorMsg().getMsg());
        }
        return renameMsg.getSuccess();
    }

    @Override
    public CFSFileStatus updateFileStatus(CFSFileStatus cfsFileStatus) throws IOException {
        final CFSFileStatus fileStatus = this.blockingStub.updateFileStatus(cfsFileStatus);
        if (fileStatus.hasErrorMsg()) {
            throw new IOException(fileStatus.getErrorMsg().getMsg());
        }
        return fileStatus;
    }
}
