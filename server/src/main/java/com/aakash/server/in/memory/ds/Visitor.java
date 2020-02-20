package com.aakash.server.in.memory.ds;

public interface Visitor<T,R> {
    R visit(T arg);
}