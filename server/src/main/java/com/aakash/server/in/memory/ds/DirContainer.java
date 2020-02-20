package com.aakash.server.in.memory.ds;

import com.aakash.server.ds.Node;
import com.aakash.server.ds.NodeAttribute;

public class DirContainer {
    public static final String name = "/";
    private final Node rootNode;

    public DirContainer(NodeAttribute inMemoryNodeAttribute) {
        this.rootNode = new Node(InMemoryNodeInfo.createDirNodeInfo(inMemoryNodeAttribute));
    }

    public Node getRootNode() {
        return this.rootNode;
    }
}
