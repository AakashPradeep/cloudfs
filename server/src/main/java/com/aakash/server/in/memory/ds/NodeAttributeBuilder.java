package com.aakash.server.in.memory.ds;

public class NodeAttributeBuilder {
    private boolean isFile;
    private short permission;
    private String owner;
    private String group;
    private long time;
    private long fileSize;
    private long blockSize;
    private int replication;

    public NodeAttributeBuilder setIsFile(boolean isFile) {
        this.isFile = isFile;
        return this;
    }

    public NodeAttributeBuilder setPermission(short permission) {
        this.permission = permission;
        return this;
    }

    public NodeAttributeBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public NodeAttributeBuilder setGroup(String group) {
        this.group = group;
        return this;
    }

    public NodeAttributeBuilder setTime(long time) {
        this.time = time;
        return this;
    }

    public NodeAttributeBuilder setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public NodeAttributeBuilder setBlockSize(long blockSize) {
        this.blockSize = blockSize;
        return this;
    }

    public NodeAttributeBuilder setReplication(int replication) {
        this.replication = replication;
        return this;
    }

    public InMemoryNodeAttribute createNodeAttribute() {
        return new InMemoryNodeAttribute(isFile, permission, owner, group, time, fileSize, blockSize, replication);
    }
}