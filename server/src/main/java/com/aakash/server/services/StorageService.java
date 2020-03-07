package com.aakash.server.services;

import com.aakash.server.ds.MultiVersionNode;
import com.aakash.server.ds.MultiVersionNode.VersionNode;
import com.aakash.server.ds.Node;
import com.aakash.server.ds.NodeInfo;
import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.off.heap.ds.OffHeapNodeInfo;
import com.aakash.server.services.TransactionService.TransactionEntry;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import static com.aakash.server.services.TransactionService.NodeEntry;

/**
 * This provide way to get an instance of {@link NodeInfoFactory} and manage a cleaning service for old {@link VersionNode}.
 */
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

    //starting another thread to asynchronously keep deleting the old {@link VersionNode}s.
    public void startCleaningService() {
        this.executorService.submit(this.oldVersionCleaner);
    }

    /**
     * Add a new {@link VersionNode} for cleanup
     * @param multiVersionNode {@link MultiVersionNode}
     * @param versionNode {@link VersionNode}
     */
    public void addVersionNodeForCleanUp(MultiVersionNode<Node> multiVersionNode, VersionNode<Node> versionNode) {
        this.oldVersionCleaner.add(multiVersionNode, versionNode);
    }

    /**
     * @return true if cleaning service running else false
     */
    public boolean isCleanerServiceHealthy() {
        return !this.executorService.isShutdown();
    }

    /**
     * An implementation of {@link Callable} to clean the old {@link VersionNode}s.
     */
    public static class OldVersionCleaner implements Callable<Void> {
        private final LinkedBlockingQueue<NodeEntry> queue = new LinkedBlockingQueue<>();
        private final LinkedBlockingQueue<TransactionEntry> transactionEventQueue = new LinkedBlockingQueue<>();
        private final TransactionService transactionService;

        public OldVersionCleaner(TransactionService transactionService) {
            this.transactionService = transactionService;
            this.transactionService.register(transactionEntry -> transactionEventQueue.add(transactionEntry));
        }

        /**
         * Add the {@link VersionNode} to be deleted. If its committed it will asynchronously delete all the {@link VersionNode}
         * following it in {@link MultiVersionNode}. If its failed one then it will delete all Node involved for current
         * transaction synchronously.
         *
         * @param multiVersionNode {@link MultiVersionNode}
         * @param needToBeDeleted  {@link VersionNode}
         */
        public void add(MultiVersionNode<Node> multiVersionNode, VersionNode<Node> needToBeDeleted) {
            if (needToBeDeleted.getVersion().isCommitted()) {
                this.queue.add(new NodeEntry(multiVersionNode, needToBeDeleted));
            }

            if (needToBeDeleted.getVersion().isFailed()) {
                for (NodeEntry nodeEntry : needToBeDeleted.getVersion().getInvolvedNodeEntrySet()) {
                    nodeEntry.getMultiVersionNode().deleteSingleVersionNode(nodeEntry.getVersionNode());
                }
            }
        }


        private boolean recClean(NodeEntry entry, TransactionEntry oldestTransactionEntryInProcess) {
            VersionNode<Node> versionNode = entry.getVersionNode();
            if (versionNode.getNext() != null) {
                return recClean(new NodeEntry(entry.getMultiVersionNode(), versionNode.getNext()), oldestTransactionEntryInProcess);
            }
            if (versionNode.getVersion().compareTo(oldestTransactionEntryInProcess) < 0) {
                entry.getMultiVersionNode().deleteSingleVersionNode(versionNode);
                versionNode.getData().free();
                return true;
            }
            return false;
        }

        @Override
        public Void call() throws Exception {
            while (true) {
                LinkedList<NodeEntry> unprocessVersionNodes = new LinkedList<>();
                for (NodeEntry nodeEntry : this.queue) {
                    TransactionEntry transactionEntryInProcess = this.transactionService.oldestInProcessTransaction();
                    if (recClean(nodeEntry, transactionEntryInProcess)) {
                        VersionNode<Node> prev = nodeEntry.getVersionNode().getPrev();
                        prev.setNext(null);
                    } else {
                        unprocessVersionNodes.addLast(nodeEntry);
                    }
                }
                this.queue.addAll(unprocessVersionNodes);
                //waiting for transaction completion notification
                this.transactionEventQueue.take();
            }
        }
    }

    public class NodeInfoFactory {
        public NodeInfo getNodeInfo(NodeInfo nodeInfo) throws SerializationException {
            if (isOffHeapEnabled) {
                return new OffHeapNodeInfo(nodeInfo);
            }
            return nodeInfo;
        }
    }
}
