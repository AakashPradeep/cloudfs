package com.aakash.server.in.memory.ds;

import com.aakash.server.exceptions.LockNotAcquiredException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Node {
    private final NodeInfo nodeInfo;
    private final Map<String, Node> children;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final char[] id = NodeIdProvider.INSTANCE.getNewUniqueId();

    public Node(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
        if (this.nodeInfo.getAttribute().isDir()) {
            this.children = new ConcurrentHashMap<>();
        } else {
            this.children = Collections.EMPTY_MAP;
        }
    }

    private Node(NodeInfo nodeInfo, Map<String, Node> children) {
        this.nodeInfo = nodeInfo;
        this.children = children;
    }

    public static Node copy(NodeInfo nodeInfo, Node node) {
        return new Node(nodeInfo, node.children);
    }

    public ReentrantReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public boolean exists(final String name) {
        return getChildNode(name).isPresent();
    }

    public Node addChildNode(final String name, final Supplier<Node> newChildNodeSupplier) throws LockNotAcquiredException, InterruptedException {
        if (this.readWriteLock.readLock().tryLock(0, TimeUnit.SECONDS)) {
            try {
                return this.children.computeIfAbsent(name, (k) -> newChildNodeSupplier.get());
            } finally {
                this.readWriteLock.readLock().unlock();
            }

        }
        throw new LockNotAcquiredException("please acquire readWriteLock before adding child node");
    }

    public Optional<Node> getChildNode(String childNodeName) {
        Optional<Node> childNode = Optional.ofNullable(this.children.get(childNodeName));
        return childNode.filter(n -> {
            NodeInfo.Status status = n.nodeInfo.getStatus();
            return status == NodeInfo.Status.COMPLETED || status == NodeInfo.Status.UNDER_WRITING;
        });
    }

    public Set<String> getChildFileNames() {
        return this.children.keySet();
    }

    public boolean isEmptyDir() throws LockNotAcquiredException {
        if (this.readWriteLock.writeLock().isHeldByCurrentThread()) {
            if (this.nodeInfo.getAttribute().isDir()) {
                if (this.children.isEmpty()) {
                    return true;
                }

                for (Map.Entry<String, Node> entry : this.children.entrySet()) {
                    NodeInfo.Status status = entry.getValue().nodeInfo.getStatus();
                    if (status != NodeInfo.Status.DELETED || status != NodeInfo.Status.UNDER_DELETE) {
                        return false;
                    }
                }
            }
            return true;
        }
        throw new LockNotAcquiredException("please acquire readWriteLock before checking isEmpty() dir status");
    }

    public Node removeChildNode(final String childName) throws LockNotAcquiredException, InterruptedException {
        if (this.readWriteLock.readLock().tryLock(0, TimeUnit.MILLISECONDS)) {
            try {
                return this.children.remove(childName);
            } finally {
                this.readWriteLock.readLock().unlock();
            }
        }
        throw new LockNotAcquiredException("please acquire readWriteLock before removing child node");
    }

    public Node computeIfPresent(String name, BiFunction<String, Node, Node> remappingFunction) throws LockNotAcquiredException, InterruptedException {
        if (this.readWriteLock.readLock().tryLock(0, TimeUnit.SECONDS)) {
            try {

                return this.children.computeIfPresent(name, remappingFunction);
            } finally {
                this.readWriteLock.readLock().unlock();
            }
        }
        throw new LockNotAcquiredException("please acquire readWriteLock before replacing child node");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeInfo, node.nodeInfo) &&
                Objects.equals(children, node.children) && Objects.equals(getId(), node.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeInfo, children, id);
    }

    public String getId() {
        return new String(id);
    }
}
