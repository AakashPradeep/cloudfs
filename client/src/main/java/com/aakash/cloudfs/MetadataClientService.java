package com.aakash.cloudfs;

import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus;
import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap;
import com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MetadataClientService {
    NamespaceInfo registerNewNamespace(String user, String group, String namespace, String vendorURI, String bucketname, Map<String, String> additionalInfo) throws IOException;

    NamespaceInfo getNamespaceInfo(String namspace) throws IOException;

    boolean mkdirs(String namespace, Path f, FsPermission permission, String owner, List<String> groups) throws IOException;

    CFSFileStatus getFileStatus(String namespace, Path f, String owner, List<String> groups) throws IOException;

    CFSFileStatusMap listStatus(String namespace, Path f, String owner, List<String> groups) throws IOException;

    boolean createZeroByteFile(String namespace, Path f, Path vendorPath, String owner, List<String> groups, short replication, long blockSize) throws IOException;

    boolean createOnUploadCompletion(String namespace, Path f, Path vendorPath, String owner, List<String> groups, short replication, long blockSize, long fileSize) throws IOException;

    boolean delete(String namespace, Path f, boolean recursive, String owner, List<String> groups) throws IOException;

    boolean rename(String namespace, Path srcPath, Path dstPath, String owner, List<String> groups) throws IOException;

    CFSFileStatus updateFileStatus(CFSFileStatus cfsFileStatus) throws IOException;
}
