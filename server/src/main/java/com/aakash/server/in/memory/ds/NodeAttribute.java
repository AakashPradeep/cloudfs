package com.aakash.server.in.memory.ds;

public interface NodeAttribute {
    long getBlockSize();

    int getReplication();

    long getFileSize();

    boolean isDir();

    boolean isFile();

    short getPermission();

    String getOwner();

    String getGroup();

    long getCreatedTime();

    long getLastModifiedTime();

    default void free() {}
}
