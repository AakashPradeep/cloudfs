package com.aakash.server.services;

import com.aakash.server.in.memory.ds.Node;

import java.util.Stack;
import java.util.function.Function;

public interface Visitor<T> {
    VisitorFunction<Node, T> getAction();

    default T visit(Node node) throws Exception {
        return getAction().visit(node);
    }

    interface VisitorFunction<Node,T>{
        T visit(Node node)throws Exception;
    }

    Visitor<Boolean> DO_NOTHING_VISITOR = () -> node -> true;
}
