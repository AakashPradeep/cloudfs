package com.aakash.server.impl;

import com.aakash.cloudfs.protocol.proto.generated.stubs.*;
import com.aakash.server.exceptions.NamespaceCannotBeCreated;
import com.aakash.server.exceptions.NamespaceDoesNotExist;
import com.aakash.server.exceptions.NamsepaceAlreadyExistException;
import com.aakash.server.services.FileOperationService;
import com.aakash.server.services.NamespaceContainerService;
import com.aakash.server.services.ServiceProvider;
import com.aakash.server.services.TransactionService;
import io.grpc.stub.StreamObserver;

public class MetadataGrpcService extends CloudFSServiceGrpc.CloudFSServiceImplBase {

    private final TransactionService transactionService = ServiceProvider.getSingeltonInstance().getTransactionService();

    @Override
    public void registerNameSpace(NamespaceInfoReq request, StreamObserver<NamespaceInfo> responseObserver) {
        NamespaceContainerService containerService = ServiceProvider.getSingeltonInstance().getNamespaceContainerService();
        try {
            containerService.registerNewNamespace(request.getOwner(), request.getGroup(), request.getName().getName(),
                    request.getVendorUri(), request.getBucketName(), request.getAdditionalInfoMap());

            responseObserver.onNext(NamespaceInfo.newBuilder().setName(request.getName().getName())
                    .setUri(request.getVendorUri())
                    .setBucketName(request.getBucketName())
                    .putAllAdditionalInfo(request.getAdditionalInfoMap()).build());
        } catch (NamespaceCannotBeCreated | NamsepaceAlreadyExistException e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            responseObserver.onNext(NamespaceInfo.newBuilder().setErrorMsg(errorMsg).build());
        }
        responseObserver.onCompleted();
    }


    @Override
    public void getNamespaceInfo(NamespaceName request, StreamObserver<NamespaceInfo> responseObserver) {
        try {
            NamespaceContainerService containerService = ServiceProvider.getSingeltonInstance().getNamespaceContainerService();
            com.aakash.bean.NamespaceInfo namespaceInfo = containerService.getNamespaceInfo(request.getName());
            NamespaceInfo build = NamespaceInfo.newBuilder()
                    .setBucketName(namespaceInfo.getBucketName())
                    .setName(namespaceInfo.getNamespace())
                    .setUri(namespaceInfo.getCloudVendorURI())
                    .putAllAdditionalInfo(namespaceInfo.getAdditionalInfo())
                    .build();
            responseObserver.onNext(build);
        } catch (NamespaceDoesNotExist e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            responseObserver.onNext(NamespaceInfo.newBuilder().setErrorMsg(errorMsg).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void exists(FSPathReq request, StreamObserver<ExistMsg> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.readTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            ExistMsg exists = service.exists(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(exists);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            ExistMsg existMsg = ExistMsg.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(existMsg);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getFileStatus(FSPathReq request, StreamObserver<CFSFileStatus> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.readTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            CFSFileStatus fileStatus = service.getFileStatus(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(fileStatus);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            CFSFileStatus fileStatus = CFSFileStatus.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(fileStatus);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getFileStatusMap(FSPathReq request, StreamObserver<CFSFileStatusMap> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.readTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            CFSFileStatusMap fileStatusMap = service.getFileStatusMap(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(fileStatusMap);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            CFSFileStatusMap fileStatusMap = CFSFileStatusMap.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(fileStatusMap);
        }
        responseObserver.onCompleted();
    }


    @Override
    public void delete(DeleteFSReq request, StreamObserver<ResultMsg> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.newWriteTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            ResultMsg resultMsg = service.delete(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(resultMsg);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            ResultMsg resultMsg = ResultMsg.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(resultMsg);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void rename(RenameFSPath request, StreamObserver<RenameMsg> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.newWriteTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            RenameMsg renameMsg = service.rename(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(renameMsg);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            RenameMsg renameMsg = RenameMsg.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(renameMsg);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void mkdirs(DirReq request, StreamObserver<ResultMsg> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.newWriteTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            ResultMsg resultMsg = service.mkdirs(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(resultMsg);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            ResultMsg resultMsg = ResultMsg.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(resultMsg);
        }
        responseObserver.onCompleted();
    }


    @Override
    public void updateFileStatus(CFSFileStatus request, StreamObserver<CFSFileStatus> responseObserver) {
        super.updateFileStatus(request, responseObserver);
    }

    @Override
    public void createZeroByteFile(CreateFileReq request, StreamObserver<ResultMsg> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.newWriteTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            ResultMsg resultMsg = service.createZeroByteFile(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(resultMsg);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            ResultMsg resultMsg = ResultMsg.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(resultMsg);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void createOnUploadCompletion(CreateFileReq request, StreamObserver<ResultMsg> responseObserver) {
        TransactionService.TransactionEntry transactionEntry = this.transactionService.newWriteTrxId();
        try {
            FileOperationService service = ServiceProvider.getSingeltonInstance().getFileOperationService();
            ResultMsg resultMsg = service.createOnUploadCompletion(transactionEntry, request);
            this.transactionService.markComitted(transactionEntry);
            responseObserver.onNext(resultMsg);
        } catch (Exception e) {
            ErrorMsg errorMsg = ErrorMsg.newBuilder().setMsg(e.getMessage()).setType(e.getClass().getName()).build();
            ResultMsg resultMsg = ResultMsg.newBuilder().setErrorMsg(errorMsg).build();
            this.transactionService.markFailed(transactionEntry);
            responseObserver.onNext(resultMsg);
        }
        responseObserver.onCompleted();
    }
}
