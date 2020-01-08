package com.aakash.server.services;

import com.aakash.server.in.memory.ds.Node;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class LockHandler implements Visitor<Boolean> {

    private final Stack<Node> stack = new Stack<>();
    private final long timeOutInMillis;

    public LockHandler(long timeOutInMillis) {
        this.timeOutInMillis = timeOutInMillis;
    }

    protected boolean lock(Node node) throws InterruptedException {
        if(timeOutInMillis <= 0){
            node.getReadWriteLock().readLock().lockInterruptibly();
            return true;
        }
        return node.getReadWriteLock().readLock().tryLock(timeOutInMillis, TimeUnit.MILLISECONDS);
    }

    public void unlock() {
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            pop.getReadWriteLock().readLock().unlock();
        }
    }

    @Override
    public VisitorFunction<Node, Boolean> getAction() {
        return node -> {
            boolean lock = lock(node);
            stack.push(node);
            return lock;
        };
    }

}
