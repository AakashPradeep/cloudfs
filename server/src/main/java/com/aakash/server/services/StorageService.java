package com.aakash.server.services;

import com.aakash.server.ds.MultiVersionNode.VersionNode;
import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.in.memory.ds.*;
import com.aakash.server.off.heap.ds.OffHeapNodeAttribute;
import com.aakash.server.off.heap.ds.OffHeapNodeInfo;
import com.aakash.server.services.TransactionService.TransactionEntry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class StorageService {
    public static final String CLOUDS_OFF_HEAP_STORAGE_ENABLED = "clouds.off.heap.storage.enabled";
    private final boolean isOffHeapEnabled;
    private final TransactionService transactionService;
    private final OldVersionCleaner oldVersionCleaner;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public StorageService(boolean isOffHeapEnabled, TransactionService transactionService) {
        this.isOffHeapEnabled = isOffHeapEnabled;
        this.transactionService = transactionService;
        this.oldVersionCleaner = new OldVersionCleaner(transactionService);
    }

    public void startCleaningService() {
        this.executorService.submit(this.oldVersionCleaner);
    }

    public boolean isCleanerServiceHealthy(){
        return !this.executorService.isShutdown();
    }

    public class NodeInfoFactory {
        public NodeInfo getNodeInfo(NodeInfo nodeInfo) throws SerializationException {
            if (isOffHeapEnabled) {
                return new OffHeapNodeInfo(nodeInfo);
            }
            return nodeInfo;
        }
    }

    public static class OldVersionCleaner implements Callable<Void> {
        private final LinkedBlockingQueue<VersionNode<Node>> queue = new LinkedBlockingQueue<>();
        private final TransactionService transactionService;

        public OldVersionCleaner(TransactionService transactionService) {
            this.transactionService = transactionService;
        }

        public void add(VersionNode<Node> versionNode) {
            this.queue.add(versionNode);
        }


        private boolean recClean(VersionNode<Node> versionNode, TransactionEntry transactionEntry) {
            if (versionNode.getNext() != null) {
                if (recClean(versionNode.getNext(), transactionEntry)) {
                    versionNode.setNext(null);
                }
            }
            if (versionNode.getVersion().compareTo(transactionEntry) < 0) {
                versionNode.getData().free();
                return true;
            }
            return false;
        }

        @Override
        public Void call() throws Exception {
            while (true) {
                TransactionEntry transactionEntry = this.transactionService.oldestInProcessTransaction();
                VersionNode<Node> versionNode = this.queue.take();
                if (recClean(versionNode, transactionEntry)) {
                    VersionNode<Node> prev = versionNode.getPrev();
                    prev.setNext(null);
                    versionNode = null;
                } else {
                    this.queue.add(versionNode);
                }
            }
        }
    }
}
