// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

public interface FSPathReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cloudfs.protocol.FSPathReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.cloudfs.protocol.NamespaceName namespace = 1;</code>
   * @return Whether the namespace field is set.
   */
  boolean hasNamespace();
  /**
   * <code>.cloudfs.protocol.NamespaceName namespace = 1;</code>
   * @return The namespace.
   */
  com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName getNamespace();
  /**
   * <code>.cloudfs.protocol.NamespaceName namespace = 1;</code>
   */
  com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceNameOrBuilder getNamespaceOrBuilder();

  /**
   * <code>string path = 2;</code>
   * @return The path.
   */
  java.lang.String getPath();
  /**
   * <code>string path = 2;</code>
   * @return The bytes for path.
   */
  com.google.protobuf.ByteString
      getPathBytes();

  /**
   * <code>string owner = 3;</code>
   * @return The owner.
   */
  java.lang.String getOwner();
  /**
   * <code>string owner = 3;</code>
   * @return The bytes for owner.
   */
  com.google.protobuf.ByteString
      getOwnerBytes();

  /**
   * <code>repeated string groups = 4;</code>
   * @return A list containing the groups.
   */
  java.util.List<java.lang.String>
      getGroupsList();
  /**
   * <code>repeated string groups = 4;</code>
   * @return The count of groups.
   */
  int getGroupsCount();
  /**
   * <code>repeated string groups = 4;</code>
   * @param index The index of the element to return.
   * @return The groups at the given index.
   */
  java.lang.String getGroups(int index);
  /**
   * <code>repeated string groups = 4;</code>
   * @param index The index of the value to return.
   * @return The bytes of the groups at the given index.
   */
  com.google.protobuf.ByteString
      getGroupsBytes(int index);
}
