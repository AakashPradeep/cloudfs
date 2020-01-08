package com.aakash.server.in.memory.ds;

import com.aakash.server.services.UTCTimeProvider;
import org.apache.hadoop.fs.Path;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TrashQueue {
    public static final UTCTimeProvider utcTimeProvider = new UTCTimeProvider();
    private final ConcurrentLinkedQueue<TrashNode> trashQueue = new ConcurrentLinkedQueue<>();

    public void add(Node node, Path path) {
        this.trashQueue.add(new TrashNode(path, node));
    }

    public boolean isNotEmpty() {
        return !this.trashQueue.isEmpty();
    }

    public TrashNode poll() {
        return this.trashQueue.poll();
    }

    public TrashNode peek() {
        return this.trashQueue.peek();
    }

    public static class TrashNode {
        private final Path path;
        private final Node node;
        private final long updateTime = utcTimeProvider.currentEpochTime();
        public TrashNode(Path path, Node node) {
            this.path = path;
            this.node = node;
        }

        public Path getPath() {
            return path;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public Node getNode() {
            return node;
        }
    }

}
