package com.aakash.in.memory.ds;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockNode {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final LockNode parent;
    private final String name;
    private final ConcurrentHashMap<String, LockNode> children = new ConcurrentHashMap<>(4);

    public LockNode(String name, LockNode parent) {
        this.parent = parent;
        this.name = name;
    }

    public LockNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public ReentrantLock getWriteLock() {
        return reentrantLock;
    }

    public LockNode addChildren(String childName) {
        return this.children.computeIfAbsent(childName, (k) -> new LockNode(k, this));
    }

    public LockNode getChildNode(String childName) {
        return this.children.get(childName);
    }

    public void removeChild(String childName) {
        this.children.computeIfPresent(childName, (s, lockNode) -> null);
    }

    public void lock() {
        this.reentrantLock.lock();
    }

    public boolean lock(long timeOutInMillis) throws InterruptedException {
        return this.reentrantLock.tryLock(timeOutInMillis, TimeUnit.MILLISECONDS);
    }

    public void free() {
        if (this.reentrantLock.isHeldByCurrentThread()) {
            this.reentrantLock.unlock();
        }

        if (this.parent != null && !this.reentrantLock.hasQueuedThreads() && !this.reentrantLock.isLocked() && this.children.isEmpty()) {
            this.parent.removeChild(this.name);
        }
    }

    @Override
    public String toString() {
        return "LockNode{" +
                "isLocked=" + this.reentrantLock.isLocked() +
                ",numThreadsWaiting=" + this.reentrantLock.getQueueLength() +
                ", parent=" + (this.parent != null ? parent.name : null) +
                ", name='" + this.name + '\'' +
                ", children=" + this.children.keySet() +
                '}';
    }
}
