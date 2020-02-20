package com.aakash.server.ds;

import com.aakash.server.exceptions.SerializationException;

public interface Writable<T> {
    byte[] write(T obj) throws SerializationException;
}
