package com.aakash.server.services;

import com.aakash.server.ds.Node;

public interface Visitor<T> {
    Visitor<Boolean> DO_NOTHING_VISITOR = () -> node -> true;

    VisitorFunction<Node, T> getAction();

    default T visit(Node node) throws Exception {
        return getAction().visit(node);
    }

    interface VisitorFunction<Node, T> {
        T visit(Node node) throws Exception;
    }
}
