package com.aakash.server.in.memory.ds;

public class DirContainer {
    public static final String name = "/";
    private final Node rootNode;

    public DirContainer(NodeAttribute nodeAttribute) {
        this.rootNode = new Node(NodeInfo.createDirNodeInfo(nodeAttribute));
    }

    public Node getRootNode() {
        return this.rootNode;
    }
}
