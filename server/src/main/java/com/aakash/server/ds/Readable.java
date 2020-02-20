package com.aakash.server.ds;

import com.aakash.server.exceptions.SerializationException;

public interface Readable<T> {
    T read(byte[] bytes) throws SerializationException;
}
