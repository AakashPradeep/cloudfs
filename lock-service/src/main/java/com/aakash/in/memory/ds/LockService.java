package com.aakash.in.memory.ds;

import java.nio.file.Paths;
import java.util.stream.StreamSupport;

public class LockService {
    private final LockNode ROOT = new LockNode("/", null);

    public boolean lock(String path) {
        final String[] pathSplits = getPaths(path);
        LockNode tmpNode = ROOT;
        for (String split : pathSplits) {
            tmpNode = tmpNode.addChildren(split);
        }
        if (tmpNode != null) {
            tmpNode.lock();
            return true;
        }
        return false;
    }

    private String[] getPaths(String path) {
        return StreamSupport.stream(Paths.get(path).spliterator(), false).map(p -> p.toString()).toArray(String[]::new);
    }

    public boolean lockWithTimeOut(String path, long timeOutInMillis) throws InterruptedException {
        final String[] pathSplits = getPaths(path);
        LockNode tmpNode = ROOT;
        for (String split : pathSplits) {
            tmpNode = tmpNode.addChildren(split);
        }
        if (tmpNode != null) {
            return tmpNode.lock(timeOutInMillis);
        }
        return false;
    }

    public boolean unlock(String path) {
        final String[] pathSplits = getPaths(path);
        LockNode tmpNode = ROOT;
        for (String split : pathSplits) {
            tmpNode = tmpNode.getChildNode(split);
            if (tmpNode == null) {
                return false;
            }
        }


        do {
            tmpNode.free();
            tmpNode = tmpNode.getParent();
        } while (tmpNode != null);

        return true;
    }
}
