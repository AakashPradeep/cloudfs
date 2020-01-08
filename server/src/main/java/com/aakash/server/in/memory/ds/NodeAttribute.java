package com.aakash.server.in.memory.ds;

public class NodeAttribute {
    private final boolean isFile;
    private char[] owner;
    private char[] group;
    private short permission;
    private long createdTime;
    private long lastModifiedTime;
    private long lastAccessedTime;
    private long fileSize;
    private long blockSize = 0;
    private int replication = 1;

    public NodeAttribute(boolean isFile, short permission, String owner, String group, long time, long fileSize) {
        this.isFile = isFile;
        this.permission = permission;
        this.createdTime = time;
        this.lastModifiedTime = time;
        this.lastAccessedTime = time;
        this.owner = owner.toCharArray();
        this.fileSize = fileSize;
        this.group = group.toCharArray();
    }

    public static NodeAttribute createDirNodeAttribute(short permission, String owner, String group, long time) {
        return new NodeAttribute(false, permission, owner, group, time, 0l);
    }

    public static NodeAttribute createFileNodeAttribute(short permission, String owner, String group, long time) {
        return new NodeAttribute(true, permission, owner, group, time, 0l);
    }

    public long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(long blockSize) {
        this.blockSize = blockSize;
    }

    public int getReplication() {
        return replication;
    }

    public void setReplication(int replication) {
        this.replication = replication;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isDir() {
        return !isFile;
    }

    public boolean isFile() {
        return isFile;
    }

    public short getPermission() {
        return permission;
    }

    public void setPermission(short permission) {
        this.permission = permission;
    }

    public String getOwner() {
        return new String(owner);
    }

    public void setOwner(char[] owner) {
        this.owner = owner;
    }

    public String getGroup() {
        return new String(group);
    }

    public void setGroup(String group) {
        this.group = group.toCharArray();
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }
}


