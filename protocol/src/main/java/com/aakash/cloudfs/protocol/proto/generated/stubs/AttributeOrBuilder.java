// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

public interface AttributeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cloudfs.protocol.Attribute)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool isFile = 1;</code>
   * @return The isFile.
   */
  boolean getIsFile();

  /**
   * <code>string owner = 2;</code>
   * @return The owner.
   */
  java.lang.String getOwner();
  /**
   * <code>string owner = 2;</code>
   * @return The bytes for owner.
   */
  com.google.protobuf.ByteString
      getOwnerBytes();

  /**
   * <code>string group = 3;</code>
   * @return The group.
   */
  java.lang.String getGroup();
  /**
   * <code>string group = 3;</code>
   * @return The bytes for group.
   */
  com.google.protobuf.ByteString
      getGroupBytes();

  /**
   * <code>int32 permission = 4;</code>
   * @return The permission.
   */
  int getPermission();

  /**
   * <code>int64 createdTime = 5;</code>
   * @return The createdTime.
   */
  long getCreatedTime();

  /**
   * <code>int64 lastModifiedTime = 6;</code>
   * @return The lastModifiedTime.
   */
  long getLastModifiedTime();

  /**
   * <code>int64 lastAccessedTime = 7;</code>
   * @return The lastAccessedTime.
   */
  long getLastAccessedTime();

  /**
   * <code>int64 fileSize = 8;</code>
   * @return The fileSize.
   */
  long getFileSize();

  /**
   * <code>int32 replication = 9;</code>
   * @return The replication.
   */
  int getReplication();

  /**
   * <code>int64 blockSize = 10;</code>
   * @return The blockSize.
   */
  long getBlockSize();
}
