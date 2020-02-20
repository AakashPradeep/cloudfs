package com.aakash.server.off.heap.ds;

import com.aakash.server.ds.Companion;
import com.aakash.server.exceptions.SerializationException;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

public abstract class AbstractCompanion<T> implements Companion<T> {
    protected int read(byte[] src, int s, byte[] dest) throws SerializationException {
        if ((s + dest.length) > src.length) {
            throw new SerializationException("result byte array (" + src.length + ") is smaller then expected (" + (s + dest.length) + ")");
        }
        for (int i = 0; i < dest.length; i++) {
            dest[i] = src[s + i];
        }
        return s + dest.length - 1;
    }

    protected int copy(byte[] r, int s, byte[] src) throws SerializationException {
        if ((s + src.length) > r.length) {
            throw new SerializationException("result byte array (" + r.length + ") is smaller then expected (" + (s + src.length) + ")");
        }
        for (int i = 0; i < src.length; i++) {
            r[s + i] = src[i];
        }
        return s + src.length - 1;
    }

    protected IndexWithValue<Long> readLong(byte[] res, int s) throws SerializationException {
        byte[] longInBytes = new byte[8];
        final int index = read(res, s, longInBytes);
        final long value = Longs.fromByteArray(longInBytes);
        return new IndexWithValue<>(index, value);
    }

    protected IndexWithValue<Integer> readInt(byte[] res, int s) throws SerializationException {
        byte[] intInBytes = new byte[4];
        final int index = read(res, s, intInBytes);
        final Integer value = Ints.fromByteArray(intInBytes);
        return new IndexWithValue<>(index, value);
    }

    public static class IndexWithValue<T> {
        public final int index;
        public final T value;

        private IndexWithValue(int index, T value) {
            this.index = index;
            this.value = value;
        }
    }

}
