// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

public interface NamespaceInfoReqOrBuilder extends
        // @@protoc_insertion_point(interface_extends:cloudfs.protocol.NamespaceInfoReq)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string owner = 1;</code>
     *
     * @return The owner.
     */
    java.lang.String getOwner();

    /**
     * <code>string owner = 1;</code>
     *
     * @return The bytes for owner.
     */
    com.google.protobuf.ByteString
    getOwnerBytes();

    /**
     * <code>string group = 2;</code>
     *
     * @return The group.
     */
    java.lang.String getGroup();

    /**
     * <code>string group = 2;</code>
     *
     * @return The bytes for group.
     */
    com.google.protobuf.ByteString
    getGroupBytes();

    /**
     * <code>.cloudfs.protocol.NamespaceName name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>.cloudfs.protocol.NamespaceName name = 3;</code>
     *
     * @return The name.
     */
    com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceName getName();

    /**
     * <code>.cloudfs.protocol.NamespaceName name = 3;</code>
     */
    com.aakash.cloudfs.protocol.proto.generated.stubs.NamespaceNameOrBuilder getNameOrBuilder();

    /**
     * <code>string vendorUri = 4;</code>
     *
     * @return The vendorUri.
     */
    java.lang.String getVendorUri();

    /**
     * <code>string vendorUri = 4;</code>
     *
     * @return The bytes for vendorUri.
     */
    com.google.protobuf.ByteString
    getVendorUriBytes();

    /**
     * <code>string bucketName = 5;</code>
     *
     * @return The bucketName.
     */
    java.lang.String getBucketName();

    /**
     * <code>string bucketName = 5;</code>
     *
     * @return The bytes for bucketName.
     */
    com.google.protobuf.ByteString
    getBucketNameBytes();

    /**
     * <code>map&lt;string, string&gt; additionalInfo = 6;</code>
     */
    int getAdditionalInfoCount();

    /**
     * <code>map&lt;string, string&gt; additionalInfo = 6;</code>
     */
    boolean containsAdditionalInfo(
            java.lang.String key);

    /**
     * Use {@link #getAdditionalInfoMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String>
    getAdditionalInfo();

    /**
     * <code>map&lt;string, string&gt; additionalInfo = 6;</code>
     */
    java.util.Map<java.lang.String, java.lang.String>
    getAdditionalInfoMap();

    /**
     * <code>map&lt;string, string&gt; additionalInfo = 6;</code>
     */

    java.lang.String getAdditionalInfoOrDefault(
            java.lang.String key,
            java.lang.String defaultValue);

    /**
     * <code>map&lt;string, string&gt; additionalInfo = 6;</code>
     */

    java.lang.String getAdditionalInfoOrThrow(
            java.lang.String key);
}
