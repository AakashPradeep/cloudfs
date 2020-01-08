// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

public interface CFSFileStatusMapOrBuilder extends
        // @@protoc_insertion_point(interface_extends:cloudfs.protocol.CFSFileStatusMap)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */
    int getCfsPathToFileStatusMapCount();

    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */
    boolean containsCfsPathToFileStatusMap(
            java.lang.String key);

    /**
     * Use {@link #getCfsPathToFileStatusMapMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
    getCfsPathToFileStatusMap();

    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */
    java.util.Map<java.lang.String, com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus>
    getCfsPathToFileStatusMapMap();

    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getCfsPathToFileStatusMapOrDefault(
            java.lang.String key,
            com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus defaultValue);

    /**
     * <code>map&lt;string, .cloudfs.protocol.CFSFileStatus&gt; cfsPathToFileStatusMap = 1;</code>
     */

    com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus getCfsPathToFileStatusMapOrThrow(
            java.lang.String key);

    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     *
     * @return Whether the errorMsg field is set.
     */
    boolean hasErrorMsg();

    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     *
     * @return The errorMsg.
     */
    com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsg getErrorMsg();

    /**
     * <code>.cloudfs.protocol.ErrorMsg errorMsg = 2;</code>
     */
    com.aakash.cloudfs.protocol.proto.generated.stubs.ErrorMsgOrBuilder getErrorMsgOrBuilder();
}
