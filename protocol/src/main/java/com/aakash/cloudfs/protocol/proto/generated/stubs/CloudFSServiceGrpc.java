package com.aakash.cloudfs.protocol.proto.generated.stubs;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: cloudfs.proto")
public final class CloudFSServiceGrpc {

  private CloudFSServiceGrpc() {}

  public static final String SERVICE_NAME = "cloudfs.protocol.CloudFSService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getRegisterNameSpaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registerNameSpace",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getRegisterNameSpaceMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq, com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getRegisterNameSpaceMethod;
    if ((getRegisterNameSpaceMethod = CloudFSServiceGrpc.getRegisterNameSpaceMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getRegisterNameSpaceMethod = CloudFSServiceGrpc.getRegisterNameSpaceMethod) == null) {
          CloudFSServiceGrpc.getRegisterNameSpaceMethod = getRegisterNameSpaceMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq, com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registerNameSpace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("registerNameSpace"))
              .build();
        }
      }
    }
    return getRegisterNameSpaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName,
      com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getGetNamespaceInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNamespaceInfo",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName,
      com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getGetNamespaceInfoMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName, com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getGetNamespaceInfoMethod;
    if ((getGetNamespaceInfoMethod = CloudFSServiceGrpc.getGetNamespaceInfoMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getGetNamespaceInfoMethod = CloudFSServiceGrpc.getGetNamespaceInfoMethod) == null) {
          CloudFSServiceGrpc.getGetNamespaceInfoMethod = getGetNamespaceInfoMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName, com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNamespaceInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("getNamespaceInfo"))
              .build();
        }
      }
    }
    return getGetNamespaceInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg> getExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exists",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg> getExistsMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg> getExistsMethod;
    if ((getExistsMethod = CloudFSServiceGrpc.getExistsMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getExistsMethod = CloudFSServiceGrpc.getExistsMethod) == null) {
          CloudFSServiceGrpc.getExistsMethod = getExistsMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("exists"))
              .build();
        }
      }
    }
    return getExistsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getGetFileStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFileStatus",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getGetFileStatusMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getGetFileStatusMethod;
    if ((getGetFileStatusMethod = CloudFSServiceGrpc.getGetFileStatusMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getGetFileStatusMethod = CloudFSServiceGrpc.getGetFileStatusMethod) == null) {
          CloudFSServiceGrpc.getGetFileStatusMethod = getGetFileStatusMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFileStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("getFileStatus"))
              .build();
        }
      }
    }
    return getGetFileStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap> getGetFileStatusMapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFileStatusMap",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap> getGetFileStatusMapMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap> getGetFileStatusMapMethod;
    if ((getGetFileStatusMapMethod = CloudFSServiceGrpc.getGetFileStatusMapMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getGetFileStatusMapMethod = CloudFSServiceGrpc.getGetFileStatusMapMethod) == null) {
          CloudFSServiceGrpc.getGetFileStatusMapMethod = getGetFileStatusMapMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFileStatusMap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("getFileStatusMap"))
              .build();
        }
      }
    }
    return getGetFileStatusMapMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getDeleteMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getDeleteMethod;
    if ((getDeleteMethod = CloudFSServiceGrpc.getDeleteMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getDeleteMethod = CloudFSServiceGrpc.getDeleteMethod) == null) {
          CloudFSServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath,
      com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg> getRenameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rename",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath,
      com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg> getRenameMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath, com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg> getRenameMethod;
    if ((getRenameMethod = CloudFSServiceGrpc.getRenameMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getRenameMethod = CloudFSServiceGrpc.getRenameMethod) == null) {
          CloudFSServiceGrpc.getRenameMethod = getRenameMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath, com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rename"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("rename"))
              .build();
        }
      }
    }
    return getRenameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getMkdirsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "mkdirs",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getMkdirsMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getMkdirsMethod;
    if ((getMkdirsMethod = CloudFSServiceGrpc.getMkdirsMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getMkdirsMethod = CloudFSServiceGrpc.getMkdirsMethod) == null) {
          CloudFSServiceGrpc.getMkdirsMethod = getMkdirsMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "mkdirs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("mkdirs"))
              .build();
        }
      }
    }
    return getMkdirsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getCreateZeroByteFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createZeroByteFile",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getCreateZeroByteFileMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getCreateZeroByteFileMethod;
    if ((getCreateZeroByteFileMethod = CloudFSServiceGrpc.getCreateZeroByteFileMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getCreateZeroByteFileMethod = CloudFSServiceGrpc.getCreateZeroByteFileMethod) == null) {
          CloudFSServiceGrpc.getCreateZeroByteFileMethod = getCreateZeroByteFileMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createZeroByteFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("createZeroByteFile"))
              .build();
        }
      }
    }
    return getCreateZeroByteFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getCreateOnUploadCompletionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createOnUploadCompletion",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq,
      com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getCreateOnUploadCompletionMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> getCreateOnUploadCompletionMethod;
    if ((getCreateOnUploadCompletionMethod = CloudFSServiceGrpc.getCreateOnUploadCompletionMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getCreateOnUploadCompletionMethod = CloudFSServiceGrpc.getCreateOnUploadCompletionMethod) == null) {
          CloudFSServiceGrpc.getCreateOnUploadCompletionMethod = getCreateOnUploadCompletionMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq, com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createOnUploadCompletion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("createOnUploadCompletion"))
              .build();
        }
      }
    }
    return getCreateOnUploadCompletionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getUpdateFileStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateFileStatus",
      requestType = com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.class,
      responseType = com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus,
      com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getUpdateFileStatusMethod() {
    io.grpc.MethodDescriptor<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getUpdateFileStatusMethod;
    if ((getUpdateFileStatusMethod = CloudFSServiceGrpc.getUpdateFileStatusMethod) == null) {
      synchronized (CloudFSServiceGrpc.class) {
        if ((getUpdateFileStatusMethod = CloudFSServiceGrpc.getUpdateFileStatusMethod) == null) {
          CloudFSServiceGrpc.getUpdateFileStatusMethod = getUpdateFileStatusMethod =
              io.grpc.MethodDescriptor.<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateFileStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus.getDefaultInstance()))
              .setSchemaDescriptor(new CloudFSServiceMethodDescriptorSupplier("updateFileStatus"))
              .build();
        }
      }
    }
    return getUpdateFileStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CloudFSServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CloudFSServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CloudFSServiceStub>() {
        @java.lang.Override
        public CloudFSServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CloudFSServiceStub(channel, callOptions);
        }
      };
    return CloudFSServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CloudFSServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CloudFSServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CloudFSServiceBlockingStub>() {
        @java.lang.Override
        public CloudFSServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CloudFSServiceBlockingStub(channel, callOptions);
        }
      };
    return CloudFSServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CloudFSServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CloudFSServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CloudFSServiceFutureStub>() {
        @java.lang.Override
        public CloudFSServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CloudFSServiceFutureStub(channel, callOptions);
        }
      };
    return CloudFSServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CloudFSServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerNameSpace(com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterNameSpaceMethod(), responseObserver);
    }

    /**
     */
    public void getNamespaceInfo(com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNamespaceInfoMethod(), responseObserver);
    }

    /**
     */
    public void exists(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getExistsMethod(), responseObserver);
    }

    /**
     */
    public void getFileStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileStatusMethod(), responseObserver);
    }

    /**
     */
    public void getFileStatusMap(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileStatusMapMethod(), responseObserver);
    }

    /**
     */
    public void delete(com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void rename(com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getRenameMethod(), responseObserver);
    }

    /**
     */
    public void mkdirs(com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getMkdirsMethod(), responseObserver);
    }

    /**
     */
    public void createZeroByteFile(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateZeroByteFileMethod(), responseObserver);
    }

    /**
     */
    public void createOnUploadCompletion(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateOnUploadCompletionMethod(), responseObserver);
    }

    /**
     */
    public void updateFileStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateFileStatusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterNameSpaceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo>(
                  this, METHODID_REGISTER_NAME_SPACE)))
          .addMethod(
            getGetNamespaceInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName,
                com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo>(
                  this, METHODID_GET_NAMESPACE_INFO)))
          .addMethod(
            getExistsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg>(
                  this, METHODID_EXISTS)))
          .addMethod(
            getGetFileStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>(
                  this, METHODID_GET_FILE_STATUS)))
          .addMethod(
            getGetFileStatusMapMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap>(
                  this, METHODID_GET_FILE_STATUS_MAP)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>(
                  this, METHODID_DELETE)))
          .addMethod(
            getRenameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath,
                com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg>(
                  this, METHODID_RENAME)))
          .addMethod(
            getMkdirsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>(
                  this, METHODID_MKDIRS)))
          .addMethod(
            getCreateZeroByteFileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>(
                  this, METHODID_CREATE_ZERO_BYTE_FILE)))
          .addMethod(
            getCreateOnUploadCompletionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq,
                com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>(
                  this, METHODID_CREATE_ON_UPLOAD_COMPLETION)))
          .addMethod(
            getUpdateFileStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus,
                com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>(
                  this, METHODID_UPDATE_FILE_STATUS)))
          .build();
    }
  }

  /**
   */
  public static final class CloudFSServiceStub extends io.grpc.stub.AbstractAsyncStub<CloudFSServiceStub> {
    private CloudFSServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CloudFSServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CloudFSServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerNameSpace(com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterNameSpaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNamespaceInfo(com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNamespaceInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exists(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExistsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileStatusMap(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileStatusMapMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rename(com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mkdirs(com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMkdirsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createZeroByteFile(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateZeroByteFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createOnUploadCompletion(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateOnUploadCompletionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateFileStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus request,
        io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateFileStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CloudFSServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CloudFSServiceBlockingStub> {
    private CloudFSServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CloudFSServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CloudFSServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo registerNameSpace(com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterNameSpaceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo getNamespaceInfo(com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName request) {
      return blockingUnaryCall(
          getChannel(), getGetNamespaceInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg exists(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request) {
      return blockingUnaryCall(
          getChannel(), getExistsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getFileStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request) {
      return blockingUnaryCall(
          getChannel(), getGetFileStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap getFileStatusMap(com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request) {
      return blockingUnaryCall(
          getChannel(), getGetFileStatusMapMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg delete(com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg rename(com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath request) {
      return blockingUnaryCall(
          getChannel(), getRenameMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg mkdirs(com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq request) {
      return blockingUnaryCall(
          getChannel(), getMkdirsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg createZeroByteFile(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request) {
      return blockingUnaryCall(
          getChannel(), getCreateZeroByteFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg createOnUploadCompletion(com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request) {
      return blockingUnaryCall(
          getChannel(), getCreateOnUploadCompletionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus updateFileStatus(com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus request) {
      return blockingUnaryCall(
          getChannel(), getUpdateFileStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CloudFSServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CloudFSServiceFutureStub> {
    private CloudFSServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CloudFSServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CloudFSServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> registerNameSpace(
        com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterNameSpaceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo> getNamespaceInfo(
        com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNamespaceInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg> exists(
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request) {
      return futureUnaryCall(
          getChannel().newCall(getExistsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> getFileStatus(
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap> getFileStatusMap(
        com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileStatusMapMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> delete(
        com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg> rename(
        com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath request) {
      return futureUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> mkdirs(
        com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq request) {
      return futureUnaryCall(
          getChannel().newCall(getMkdirsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> createZeroByteFile(
        com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateZeroByteFileMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg> createOnUploadCompletion(
        com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateOnUploadCompletionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus> updateFileStatus(
        com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateFileStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_NAME_SPACE = 0;
  private static final int METHODID_GET_NAMESPACE_INFO = 1;
  private static final int METHODID_EXISTS = 2;
  private static final int METHODID_GET_FILE_STATUS = 3;
  private static final int METHODID_GET_FILE_STATUS_MAP = 4;
  private static final int METHODID_DELETE = 5;
  private static final int METHODID_RENAME = 6;
  private static final int METHODID_MKDIRS = 7;
  private static final int METHODID_CREATE_ZERO_BYTE_FILE = 8;
  private static final int METHODID_CREATE_ON_UPLOAD_COMPLETION = 9;
  private static final int METHODID_UPDATE_FILE_STATUS = 10;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CloudFSServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CloudFSServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_NAME_SPACE:
          serviceImpl.registerNameSpace((com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfoReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo>) responseObserver);
          break;
        case METHODID_GET_NAMESPACE_INFO:
          serviceImpl.getNamespaceInfo((com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceInfo>) responseObserver);
          break;
        case METHODID_EXISTS:
          serviceImpl.exists((com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ExistMsg>) responseObserver);
          break;
        case METHODID_GET_FILE_STATUS:
          serviceImpl.getFileStatus((com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>) responseObserver);
          break;
        case METHODID_GET_FILE_STATUS_MAP:
          serviceImpl.getFileStatusMap((com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatusMap>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((com.aakash.cloudfs.protocol.proto.generated.stubs.DeleteFSReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>) responseObserver);
          break;
        case METHODID_RENAME:
          serviceImpl.rename((com.aakash.cloudfs.protocol.proto.generated.stubs.RenameFSPath) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.RenameMsg>) responseObserver);
          break;
        case METHODID_MKDIRS:
          serviceImpl.mkdirs((com.aakash.cloudfs.protocol.proto.generated.stubs.DirReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>) responseObserver);
          break;
        case METHODID_CREATE_ZERO_BYTE_FILE:
          serviceImpl.createZeroByteFile((com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>) responseObserver);
          break;
        case METHODID_CREATE_ON_UPLOAD_COMPLETION:
          serviceImpl.createOnUploadCompletion((com.aakash.cloudfs.protocol.proto.generated.stubs.CreateFileReq) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.ResultMsg>) responseObserver);
          break;
        case METHODID_UPDATE_FILE_STATUS:
          serviceImpl.updateFileStatus((com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus) request,
              (io.grpc.stub.StreamObserver<com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CloudFSServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CloudFSServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aakash.cloudfs.protocol.proto.generated.stubs.CloudFSServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CloudFSService");
    }
  }

  private static final class CloudFSServiceFileDescriptorSupplier
      extends CloudFSServiceBaseDescriptorSupplier {
    CloudFSServiceFileDescriptorSupplier() {}
  }

  private static final class CloudFSServiceMethodDescriptorSupplier
      extends CloudFSServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CloudFSServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CloudFSServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CloudFSServiceFileDescriptorSupplier())
              .addMethod(getRegisterNameSpaceMethod())
              .addMethod(getGetNamespaceInfoMethod())
              .addMethod(getExistsMethod())
              .addMethod(getGetFileStatusMethod())
              .addMethod(getGetFileStatusMapMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getRenameMethod())
              .addMethod(getMkdirsMethod())
              .addMethod(getCreateZeroByteFileMethod())
              .addMethod(getCreateOnUploadCompletionMethod())
              .addMethod(getUpdateFileStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
