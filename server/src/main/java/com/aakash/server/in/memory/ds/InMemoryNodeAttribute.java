package com.aakash.server.in.memory.ds;

import com.aakash.server.ds.NodeAttribute;

import java.util.Arrays;
import java.util.Objects;

public class InMemoryNodeAttribute implements NodeAttribute {
    protected final boolean isFile;
    protected final char[] owner;
    protected final char[] group;
    protected final short permission;
    protected final long createdTime;
    protected final long fileSize;
    protected final long blockSize;
    protected final int replication;

    public InMemoryNodeAttribute(boolean isFile, short permission, String owner, String group, long time, long fileSize, long blockSize, int replication) {
        this.isFile = isFile;
        this.permission = permission;
        this.createdTime = time;
        this.owner = owner.toCharArray();
        this.fileSize = fileSize;
        this.group = group.toCharArray();
        this.blockSize = blockSize;
        this.replication = replication;
    }

    public static NodeAttribute createDirNodeAttribute(short permission, String owner, String group, long time) {
        return new NodeAttributeBuilder().setIsFile(false).setPermission(permission).setOwner(owner).setGroup(group).setTime(time).setFileSize(0l).setBlockSize(0l).setReplication(0).createNodeAttribute();
    }

    public static NodeAttribute createFileNodeAttribute(short permission, String owner, String group, long time) {
        return new NodeAttributeBuilder().setIsFile(true).setPermission(permission).setOwner(owner).setGroup(group).setTime(time).setFileSize(0l).setBlockSize(0).setReplication(1).createNodeAttribute();
    }


    @Override
    public long getBlockSize() {
        return blockSize;
    }

    @Override
    public int getReplication() {
        return replication;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public boolean isDir() {
        return !isFile;
    }

    @Override
    public boolean isFile() {
        return isFile;
    }

    @Override
    public short getPermission() {
        return permission;
    }

    @Override
    public String getOwner() {
        return new String(owner);
    }

    @Override
    public String getGroup() {
        return new String(group);
    }

    @Override
    public long getCreatedTime() {
        return createdTime;
    }

    @Override
    public long getLastModifiedTime() {
        return createdTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryNodeAttribute that = (InMemoryNodeAttribute) o;
        return isFile == that.isFile &&
                permission == that.permission &&
                createdTime == that.createdTime &&
                fileSize == that.fileSize &&
                blockSize == that.blockSize &&
                replication == that.replication &&
                Arrays.equals(owner, that.owner) &&
                Arrays.equals(group, that.group);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(isFile, permission, createdTime, fileSize, blockSize, replication);
        result = 31 * result + Arrays.hashCode(owner);
        result = 31 * result + Arrays.hashCode(group);
        return result;
    }

    @Override
    public String toString() {
        return "NodeAttribute{" +
                "isFile=" + isFile +
                ", owner=" + Arrays.toString(owner) +
                ", group=" + Arrays.toString(group) +
                ", permission=" + permission +
                ", createdTime=" + createdTime +
                ", lastModifiedTime=" + createdTime +
                ", fileSize=" + fileSize +
                ", blockSize=" + blockSize +
                ", replication=" + replication +
                '}';
    }


}


