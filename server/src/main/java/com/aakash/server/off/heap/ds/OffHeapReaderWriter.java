package com.aakash.server.off.heap.ds;

import com.aakash.server.ds.Companion;
import com.aakash.server.exceptions.SerializationException;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.apache.commons.io.Charsets;
import sun.misc.Unsafe;

public class OffHeapReaderWriter {
    public static final String EMPTY_STRING = "";
    static final OffHeapReaderWriter INSTANCE = new OffHeapReaderWriter();
    private final int baseIndex = 4;

    public <T> T read(Companion<T> companion, long memoryAddress) throws SerializationException {

        Unsafe unsafe = UnSafeProvider.getUnsafe();
        byte[] result = new byte[4];
        for (int i = 0; i < result.length; i++) {
            result[i] = unsafe.getByte(memoryAddress  + i );
        }
        int size = Ints.fromByteArray(result);

        byte[] bytes = readBytes(memoryAddress, 0, size);
        return companion.read(bytes);
    }

    public <T> long write(Companion<T> companion, T data) throws SerializationException {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        byte[] bytes = companion.write(data);
        final long memory = unsafe.allocateMemory(bytes.length + 4);
        long newMemoryAddress = putInt(memory, bytes.length);
        putBytes(newMemoryAddress + 1, bytes);
        return memory;
    }

    public long putLong(long memoryAddress, long val) {
        byte[] bytes = Longs.toByteArray(val);
        return putBytes(memoryAddress, bytes);
    }

    public long putInt(long memoryAddress, int val) {
        byte[] bytes = Ints.toByteArray(val);
        return putBytes(memoryAddress, bytes);
    }

    public long putBytes(long memoryAddress, byte[] bytes) {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        for (int i = 0; i < bytes.length; i++) {
            unsafe.putByte(memoryAddress + i, bytes[i]);
        }
        return memoryAddress + bytes.length - 1;
    }

    public Long readLong(long memoryAddress, long offset) {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        byte[] result = new byte[8];
        for (int i = 0; i < result.length; i++) {
            result[i] = unsafe.getByte(memoryAddress + baseIndex + i + offset);
        }
        return Longs.fromByteArray(result);
    }

    public Integer readInt(long memoryAddress, long offset) {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        byte[] result = new byte[4];
        for (int i = 0; i < result.length; i++) {
            result[i] = unsafe.getByte(memoryAddress + baseIndex + i + offset);
        }
        return Ints.fromByteArray(result);
    }

    public Integer readIntWithoutBaseSizeOffset(long memoryAddress, long offset) {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        byte[] result = new byte[4];
        for (int i = 0; i < result.length; i++) {
            result[i] = unsafe.getByte(memoryAddress  + i + offset);
        }
        return Ints.fromByteArray(result);
    }

    public Byte readByte(long memoryAddress, long offset) {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        return unsafe.getByte(memoryAddress + offset + baseIndex);
    }

    public byte[] readBytes(long memoryAddress, long offset, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = readByte(memoryAddress, offset + i);
        }
        return bytes;
    }


    public String readString(long memoryAddress, long offset) {
        Unsafe unsafe = UnSafeProvider.getUnsafe();
        long startAddress = memoryAddress + offset + baseIndex;
        final int size = readInt(memoryAddress, offset);
        if (size == 0) {
            return EMPTY_STRING;
        }
        byte[] result = new byte[size];
        for (int i = 0; i < size; i++) {
            result[i] = unsafe.getByte(startAddress + 4 + i);
        }
        return new String(result, Charsets.UTF_8);
    }

    public void free(long memoryAddress) {
        UnSafeProvider.getUnsafe().freeMemory(memoryAddress);
    }
}
