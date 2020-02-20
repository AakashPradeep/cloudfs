package com.aakash.cloudfs;

import com.aakash.cloudfs.protocol.proto.generated.stubs.*;
import com.aakash.server.services.FileOperationService;
import com.aakash.server.services.NamespaceContainerService;
import com.aakash.server.services.ServiceProvider;
import com.aakash.server.services.TransactionService;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class LocalInMemoryMetadataClientStoreService implements MetadataClientService {


    public NamespaceInfo registerNewNamespace(String user, String group, String namespace, String vendorURI, String bucketname, Map<String, String> additionalInfo) throws IOException {
        final NamespaceInfoReq request = NamespaceInfoReq.newBuilder().setOwner(user).setGroup(group)
                .setName(NamespaceName.newBuilder().setName(namespace).build())
                .setBucketName(bucketname)
                .setVendorUri(vendorURI)
                .putAllAdditionalInfo(additionalInfo).build();
        NamespaceContainerService containerService = ServiceProvider.getSingeltonInstance().getNamespaceContainerService();

        NamespaceInfo namespaceInfo = executeFunction(() -> {
            containerService.registerNewNamespace(request.getOwner(), request.getGroup(), request.getName().getName(),
                    request.getVendorUri(), request.getBucketName(), request.getAdditionalInfoMap());
            return NamespaceInfo.newBuilder().setName(request.getName().getName())
                    .setUri(request.getVendorUri())
                    .setBucketName(request.getBucketName())
                    .putAllAdditionalInfo(request.getAdditionalInfoMap()).build();
        });

        if (namespaceInfo.hasErrorMsg()) {
            throw new IOException("Error While creating namesapce:" + namespace, new Exception(namespaceInfo.getErrorMsg().getMsg()));
        }
        return namespaceInfo;
    }

    public NamespaceInfo getNamespaceInfo(String namspace) throws IOException {
        NamespaceName request = NamespaceName.newBuilder().setName(namspace).build();
        NamespaceContainerService containerService = ServiceProvider.getSingeltonInstance().getNamespaceContainerService();
        com.aakash.bean.NamespaceInfo namespaceInfo = containerService.getNamespaceInfo(request.getName());
        NamespaceInfo namespaceInfo1 = NamespaceInfo.newBuilder()
                .setBucketName(namespaceInfo.getBucketName())
                .setName(namespaceInfo.getNamespace())
                .setUri(namespaceInfo.getCloudVendorURI())
                .putAllAdditionalInfo(namespaceInfo.getAdditionalInfo())
                .build();
        if (namespaceInfo1.hasErrorMsg()) {
            throw new IOException(namespaceInfo1.getErrorMsg().getMsg());
        }
        return namespaceInfo1;
    }

    public boolean mkdirs(String namespace, Path f, FsPermission permission, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        final DirReq dirReq = DirReq.newBuilder().setFsPath(fsPath).setPermission(permission.toShort()).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        ResultMsg resultMsg = executeFunction(() -> service.mkdirs(transactionEntry, dirReq));
        if (!resultMsg.getSuccess()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return resultMsg.getSuccess();
    }

    private FileOperationService getFileOperationService() throws IOException {
        return executeFunction(() -> ServiceProvider.getSingeltonInstance().getFileOperationService());
    }

    private TransactionService getTransactionService() {
        return ServiceProvider.getSingeltonInstance().getTransactionService();
    }


    public CFSFileStatus getFileStatus(String namespace, Path f, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        CFSFileStatus fileStatus = executeFunction(() -> service.getFileStatus(transactionEntry, fsPath));
        if (fileStatus.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(fileStatus.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return fileStatus;
    }

    public CFSFileStatusMap listStatus(String namespace, Path f, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        CFSFileStatusMap fileStatusMap = executeFunction(() -> service.getFileStatusMap(transactionEntry, fsPath));
        if (fileStatusMap.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(fileStatusMap.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return fileStatusMap;
    }

    public boolean exists(String namespace, Path f, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        ExistMsg existMsg = executeFunction(() -> service.exists(transactionEntry, fsPath));
        if (existMsg.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(existMsg.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return existMsg.getExists();
    }

    public boolean createZeroByteFile(String namespace, Path f, Path vendorPath, String owner, List<String> groups, short replication, long blockSize) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        CreateFileReq createFileReq = CreateFileReq.newBuilder()
                .setBlockSize(blockSize)
                .setFileSize(0)
                .setReplication(replication)
                .setVendorPath(vendorPath.toString())
                .setFsPath(fsPath).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        ResultMsg resultMsg = executeFunction(() -> service.createZeroByteFile(transactionEntry, createFileReq));
        if (resultMsg.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
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
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        ResultMsg resultMsg = executeFunction(() -> service.createOnUploadCompletion(transactionEntry, createFileReq));
        if (resultMsg.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return resultMsg.getSuccess();
    }


    public boolean delete(String namespace, Path f, boolean recursive, String owner, List<String> groups) throws IOException {
        final FSPathReq fsPath = FSPathReq.newBuilder().setNamespace(NamespaceName.newBuilder().setName(namespace).build())
                .setOwner(owner).addAllGroups(groups).setPath(f.toString()).build();
        DeleteFSReq deleteFSReq = DeleteFSReq.newBuilder().setFsPathReq(fsPath).setRecursive(recursive).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        ResultMsg resultMsg = executeFunction(() -> service.delete(transactionEntry, deleteFSReq));
        if (resultMsg.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(resultMsg.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return resultMsg.getSuccess();
    }

    public boolean rename(String namespace, Path srcPath, Path dstPath, String owner, List<String> groups) throws IOException {
        RenameFSPath renameFSPath = RenameFSPath.newBuilder().setNamespace(namespace).setOwner(owner).addAllGroup(groups).setSrcPath(srcPath.toString())
                .setDstPath(dstPath.toString()).build();
        FileOperationService service = getFileOperationService();
        final TransactionService.TransactionEntry transactionEntry = getTransactionService().newWriteTrxId();
        RenameMsg renameMsg = executeFunction(() -> service.rename(transactionEntry, renameFSPath));
        if (renameMsg.hasErrorMsg()) {
            getTransactionService().markFailed(transactionEntry);
            throw new IOException(renameMsg.getErrorMsg().getMsg());
        }
        getTransactionService().markComitted(transactionEntry);
        return renameMsg.getSuccess();
    }

    public CFSFileStatus updateFileStatus(CFSFileStatus cfsFileStatus) throws IOException {
        return null;
    }

    private <T> T executeFunction(Callable<T> callable) throws IOException {
        try {
            return callable.call();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


}
