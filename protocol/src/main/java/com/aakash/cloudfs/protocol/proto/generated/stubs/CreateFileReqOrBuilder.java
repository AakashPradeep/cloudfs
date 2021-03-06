// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

public interface CreateFileReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cloudfs.protocol.CreateFileReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
   * @return Whether the fsPath field is set.
   */
  boolean hasFsPath();
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
   * @return The fsPath.
   */
  com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getFsPath();
  /**
   * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
   */
  com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getFsPathOrBuilder();

  /**
   * <code>int32 permission = 2;</code>
   * @return The permission.
   */
  int getPermission();

  /**
   * <code>int32 replication = 3;</code>
   * @return The replication.
   */
  int getReplication();

  /**
   * <code>int64 blockSize = 4;</code>
   * @return The blockSize.
   */
  long getBlockSize();

  /**
   * <code>int64 fileSize = 5;</code>
   * @return The fileSize.
   */
  long getFileSize();

  /**
   * <code>string vendorPath = 6;</code>
   * @return The vendorPath.
   */
  java.lang.String getVendorPath();
  /**
   * <code>string vendorPath = 6;</code>
   * @return The bytes for vendorPath.
   */
  com.google.protobuf.ByteString
      getVendorPathBytes();

  /**
   * <code>int64 reqTimeOutInMillis = 7;</code>
   * @return The reqTimeOutInMillis.
   */
  long getReqTimeOutInMillis();
}
