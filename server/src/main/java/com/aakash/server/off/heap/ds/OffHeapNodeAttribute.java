package com.aakash.server.off.heap.ds;

import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.in.memory.ds.InMemoryNodeAttribute;
import com.aakash.server.in.memory.ds.NodeAttribute;
import com.aakash.server.in.memory.ds.NodeAttributeBuilder;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.util.Optional;

/**
 * An off heap based impl of {@link NodeAttribute}.
 */
public class OffHeapNodeAttribute implements NodeAttribute {
    public static final int CREATE_TIME_OFFSET = 2;
    public static final int FILE_SIZE_OFFSET = CREATE_TIME_OFFSET + 8;
    public static final int BLOCK_SIZE_OFFSET = FILE_SIZE_OFFSET + 8;
    public static final int REPLICATION_OFFSET = BLOCK_SIZE_OFFSET + 8;
    public static final int OWNER_OFFSET = REPLICATION_OFFSET + 1;
    public static final Long ZERO_LONG = new Long(0l);
    static final NodeAttributeCompanion companion = new NodeAttributeCompanion();
    private final long memoryAddress;

    public OffHeapNodeAttribute(NodeAttribute obj) throws SerializationException {
        super();
        this.memoryAddress = OffHeapReaderWriter.INSTANCE.write(companion, obj);

        if (Long.valueOf(this.memoryAddress).compareTo(ZERO_LONG) <= 0) {
            throw new RuntimeException("Not able to allocate off heap memory (return code:" + this.memoryAddress
                    + ") for attribute:" + obj);
        }
    }

    OffHeapNodeAttribute(final long memoryAddress) {
        this.memoryAddress = memoryAddress;

        if (Long.valueOf(this.memoryAddress).compareTo(ZERO_LONG) <= 0) {
            throw new RuntimeException("Invalid Memory address:" + this.memoryAddress);
        }
    }

    @Override
    public void free() {
        OffHeapReaderWriter.INSTANCE.free(this.getMemoryAddress());
    }

    public long getMemoryAddress() {
        return this.memoryAddress;
    }

    @Override
    public long getBlockSize() {
        return OffHeapReaderWriter.INSTANCE.readLong(this.memoryAddress, BLOCK_SIZE_OFFSET);
    }


    @Override
    public int getReplication() {
        return OffHeapReaderWriter.INSTANCE.readByte(this.memoryAddress, REPLICATION_OFFSET).intValue();
    }

    @Override
    public long getFileSize() {
        return OffHeapReaderWriter.INSTANCE.readLong(this.memoryAddress, FILE_SIZE_OFFSET);
    }

    @Override
    public boolean isDir() {
        return !isFile();
    }

    @Override
    public boolean isFile() {
        Byte f = OffHeapReaderWriter.INSTANCE.readByte(this.memoryAddress, 0);
        return (f & 0xf0) > 0;
    }

    @Override
    public short getPermission() {
        Byte f = OffHeapReaderWriter.INSTANCE.readByte(this.memoryAddress, 0);
        Byte s = OffHeapReaderWriter.INSTANCE.readByte(this.memoryAddress, 1);
        return Integer.valueOf((f & 0x0f) * 100 + ((s & 0xf0) >> 4) * 10 + (s & 0x0f)).shortValue();
    }


    @Override
    public String getOwner() {
        return OffHeapReaderWriter.INSTANCE.readString(this.memoryAddress, OWNER_OFFSET);
    }


    @Override
    public String getGroup() {
        Integer ownerLength = OffHeapReaderWriter.INSTANCE.readInt(this.memoryAddress, OWNER_OFFSET);
        return OffHeapReaderWriter.INSTANCE.readString(this.memoryAddress, OWNER_OFFSET + 4 + ownerLength);
    }


    @Override
    public long getCreatedTime() {
        return OffHeapReaderWriter.INSTANCE.readLong(this.memoryAddress, CREATE_TIME_OFFSET);
    }

    @Override
    public long getLastModifiedTime() {
        return OffHeapReaderWriter.INSTANCE.readLong(this.memoryAddress, CREATE_TIME_OFFSET);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffHeapNodeAttribute that = (OffHeapNodeAttribute) o;

        InMemoryNodeAttribute original = getObj(this.memoryAddress);
        InMemoryNodeAttribute other = getObj(that.memoryAddress);

        return original.equals(other);
    }

    public NodeAttribute getObj(){
        return getObj(this.memoryAddress);
    }

    private InMemoryNodeAttribute getObj(long memoryOffset) {
        try {
            NodeAttribute nodeAttribute = OffHeapReaderWriter.INSTANCE.read(companion, memoryOffset);
            return new NodeAttributeBuilder()
                    .setIsFile(nodeAttribute.isFile())
                    .setPermission(nodeAttribute.getPermission())
                    .setOwner(nodeAttribute.getOwner())
                    .setGroup(nodeAttribute.getGroup())
                    .setTime(nodeAttribute.getCreatedTime())
                    .setFileSize(nodeAttribute.getFileSize())
                    .setBlockSize(nodeAttribute.getBlockSize())
                    .setReplication(nodeAttribute.getReplication())
                    .createNodeAttribute();
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int hashCode() {
        return getObj(this.memoryAddress).hashCode();
    }

    @Override
    public String toString() {
        return "OffHeapNodeAttribute{" +
                "NodeAttribute=" + getObj(this.memoryAddress).toString() +
                ", memoryAddress=" + memoryAddress +
                '}';
    }

    public static class NodeAttributeCompanion extends AbstractCompanion<NodeAttribute> {
        @Override
        public NodeAttribute read(byte[] bytes) throws SerializationException {
            byte f = bytes[0];
            byte s = bytes[1];
            final boolean isFile = (f & 0xf0) > 0;
            final short permission = Integer.valueOf((f & 0x0f) * 100 + ((s & 0xf0) >> 4) * 10 + (s & 0x0f)).shortValue();

            IndexWithValue<Long> createdTimeIndexWithValue = readLong(bytes, 2);
            IndexWithValue<Long> fileSizeIndexWithValue = readLong(bytes, createdTimeIndexWithValue.index + 1);
            IndexWithValue<Long> blockSizeIndexWithValue = readLong(bytes, fileSizeIndexWithValue.index + 1);
            final int replication = new Byte(bytes[blockSizeIndexWithValue.index + 1]).intValue();

            IndexWithValue<Integer> ownerLengthIndexWithValue = readInt(bytes, blockSizeIndexWithValue.index + 2);
            byte[] ownerInBytes = new byte[ownerLengthIndexWithValue.value];
            int index = read(bytes, ownerLengthIndexWithValue.index + 1, ownerInBytes);
            final String owner = new String(ownerInBytes);

            IndexWithValue<Integer> groupLengthIndexWithValue = readInt(bytes, index + 1);

            byte[] groupInBytes = new byte[groupLengthIndexWithValue.value];
            read(bytes, groupLengthIndexWithValue.index + 1, groupInBytes);
            final String group = new String(groupInBytes);


            NodeAttribute inMemoryNodeAttribute = new NodeAttributeBuilder()
                    .setIsFile(isFile)
                    .setPermission(permission)
                    .setOwner(owner)
                    .setGroup(group)
                    .setTime(createdTimeIndexWithValue.value)
                    .setFileSize(fileSizeIndexWithValue.value)
                    .setBlockSize(blockSizeIndexWithValue.value)
                    .setReplication(replication)
                    .createNodeAttribute();
            return inMemoryNodeAttribute;
        }


        @Override
        public byte[] write(NodeAttribute obj) throws SerializationException {
            Integer ownerPerm = obj.getPermission() / 100;
            byte f = (byte) ((((obj.isFile() ? 1 : 0) << 4) & 0xf0) | (ownerPerm.byteValue() & 0xff));
            Integer grpPerm = (obj.getPermission() % 100) / 10;
            Integer otherPerm = obj.getPermission() % 10;

            byte s = (byte) ((grpPerm.byteValue() << 4) | otherPerm.byteValue());
            byte[] owner = Optional.ofNullable(obj.getOwner()).map(s1 -> new String(s1).getBytes()).orElse(new byte[0]);
            byte[] group = Optional.ofNullable(obj.getGroup()).map(s1 -> new String(s1).getBytes()).orElse(new byte[0]);

            byte[] createdTimeInBytes = Longs.toByteArray(obj.getCreatedTime());
            byte[] ownerLengthInBytes = Ints.toByteArray(owner.length);
            final int bytesRequired = 3 + createdTimeInBytes.length * 3 + ownerLengthInBytes.length * 2 + owner.length + group.length;
            byte[] result = new byte[bytesRequired];
            result[0] = f;
            result[1] = s;
            int index = 2;
            index = copy(result, index, createdTimeInBytes);
            index = copy(result, index + 1, Longs.toByteArray(obj.getFileSize()));
            index = copy(result, index + 1, Longs.toByteArray(obj.getBlockSize()));
            result[++index] = Integer.valueOf(obj.getReplication()).byteValue();
            index = copy(result, index + 1, ownerLengthInBytes);
            index = copy(result, index + 1, owner);
            index = copy(result, index + 1, Ints.toByteArray(group.length));
            copy(result, index + 1, group);

            return result;
        }
    }
}
