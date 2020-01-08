// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cloudfs.proto

package com.aakash.cloudfs.protocol.proto.generated.stubs;

public interface DirReqOrBuilder extends
        // @@protoc_insertion_point(interface_extends:cloudfs.protocol.DirReq)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     *
     * @return Whether the fsPath field is set.
     */
    boolean hasFsPath();

    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     *
     * @return The fsPath.
     */
    com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReq getFsPath();

    /**
     * <code>.cloudfs.protocol.FSPathReq fsPath = 1;</code>
     */
    com.aakash.cloudfs.protocol.proto.generated.stubs.FSPathReqOrBuilder getFsPathOrBuilder();

    /**
     * <code>int32 permission = 2;</code>
     *
     * @return The permission.
     */
    int getPermission();

    /**
     * <code>int64 reqTimeOutInMillis = 3;</code>
     *
     * @return The reqTimeOutInMillis.
     */
    long getReqTimeOutInMillis();
}
