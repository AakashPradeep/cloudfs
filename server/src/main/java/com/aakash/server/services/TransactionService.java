package com.aakash.server.services;

import com.aakash.server.ds.MultiVersionNode;
import com.aakash.server.ds.Node;
import com.aakash.server.ds.TransactionIdProvider;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * A service for issuing new transactions and managing it.
 */
public class TransactionService {
    private final CopyOnWriteArraySet<RegisteredService> registeredServices = new CopyOnWriteArraySet<>();
    private final TransactionIdProvider transactionIdProvider;
    private final ConcurrentSkipListSet<TransactionEntry> inCompleteReadTransactionEntries = new ConcurrentSkipListSet<>();
    private final ConcurrentSkipListSet<TransactionEntry> inCompleteWriteTransactionEntries = new ConcurrentSkipListSet<>();


    public TransactionService(TransactionIdProvider transactionIdProvider) {
        this.transactionIdProvider = transactionIdProvider;
    }

    public void register(RegisteredService registeredService) {
        this.registeredServices.add(registeredService);
    }

    public void unregister(RegisteredService registeredService) {
        this.registeredServices.remove(registeredService);
    }

    public TransactionEntry newWriteTrxId() {
        TransactionEntry transactionEntry = new TransactionEntry(this.transactionIdProvider.getNewTrxId(), Type.WRITE);
        this.inCompleteWriteTransactionEntries.add(transactionEntry);
        return transactionEntry;
    }

    public TransactionEntry newReadTrxId() {
        TransactionEntry transactionEntry = new TransactionEntry(this.transactionIdProvider.getCurrentTrxId(), Type.READ);
        this.inCompleteReadTransactionEntries.add(transactionEntry);
        return transactionEntry;
    }

    public void markComitted(TransactionEntry transactionEntry) {
        transactionEntry.markCommitted();
        InternalMarkCompleteTrx(transactionEntry);
    }

    /**
     *
     * @return the oldest {@link TransactionEntry} from incomplete read and write transactions.
     */
    public TransactionEntry oldestInProcessTransaction() {
        TransactionEntry firstWriteTrx = this.inCompleteWriteTransactionEntries.first();
        TransactionEntry firstReadTrx = this.inCompleteWriteTransactionEntries.first();
        if (firstReadTrx.compareTo(firstWriteTrx) <= 0) {
            return firstReadTrx;
        }
        return firstWriteTrx;
    }

    private void InternalMarkCompleteTrx(TransactionEntry transactionEntry) {
        switch (transactionEntry.type) {
            case READ:
                this.inCompleteReadTransactionEntries.remove(transactionEntry);
                break;
            case WRITE:
                this.inCompleteWriteTransactionEntries.remove(transactionEntry);
                versionNodeCleanUp(transactionEntry);
                break;
        }


        for (RegisteredService registeredService : this.registeredServices) {
            registeredService.notifyTransactionCompletionEvent(transactionEntry);
        }
    }

    private void versionNodeCleanUp(TransactionService.TransactionEntry transactionEntry) {
        if (transactionEntry.isCommitted()) {
            for (TransactionService.NodeEntry<?> nodeEntry : transactionEntry.getInvolvedNodeEntrySet()) {
                MultiVersionNode<Node> multiVersionNode = (MultiVersionNode<Node>) nodeEntry.getMultiVersionNode();
                for (MultiVersionNode.VersionNode<Node> versionNode : multiVersionNode.getVersionNodeCleanUp()) {
                    ServiceProvider.getSingeltonInstance().getStorageService().addVersionNodeForCleanUp(multiVersionNode, versionNode);
                }
            }
        } else if (transactionEntry.isFailed()) {
            for (TransactionService.NodeEntry<?> nodeEntry : transactionEntry.getInvolvedNodeEntrySet()) {
                MultiVersionNode<Node> multiVersionNode = (MultiVersionNode<Node>) nodeEntry.getMultiVersionNode();
                MultiVersionNode.VersionNode<Node> versionNode = (MultiVersionNode.VersionNode<Node>) nodeEntry.getVersionNode();
                multiVersionNode.deleteSingleVersionNode(versionNode);
            }
        }
    }
    public void markFailed(TransactionEntry transactionEntry) {
        transactionEntry.markFailed();
        InternalMarkCompleteTrx(transactionEntry);
    }

    public enum State {
        STARTED, COMMITTED, FAILED
    }

    public enum Type {
        READ, WRITE
    }

    public interface RegisteredService {
        void notifyTransactionCompletionEvent(TransactionEntry transactionEntry);
    }

    public static class TransactionEntry implements Comparable<TransactionEntry> {
        private final long trxId;
        private final Type type;
        private final Set<NodeEntry<?>> involvedNodeEntrySet = new LinkedHashSet<>();
        private State state = State.STARTED;

        public TransactionEntry(long trxId, Type type) {
            this.trxId = trxId;
            this.type = type;

        }

        public Set<NodeEntry<?>> getInvolvedNodeEntrySet() {
            return involvedNodeEntrySet;
        }

        public void add(NodeEntry<?> nodeEntry) {
            if (!involvedNodeEntrySet.contains(nodeEntry)) {
                involvedNodeEntrySet.add(nodeEntry);
            }
        }


        public long getTrxId() {
            return trxId;
        }

        public State getState() {
            return state;
        }

        private void markCommitted() {
            this.state = State.COMMITTED;
        }

        private void markFailed() {
            this.state = State.FAILED;
        }

        public boolean isCommitted() {
            return this.state == State.COMMITTED;
        }

        public boolean isFailed() {
            return this.state == State.FAILED;
        }

        public boolean isActive() {
            return this.state == State.STARTED;
        }

        @Override
        public int compareTo(TransactionEntry o) {
            final int compare = new Long(this.trxId - o.trxId).intValue();
            int result = compare == 0 ? this.getState().compareTo(o.getState()) : compare;
            return result == 0 ? this.type.compareTo(o.type) : result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TransactionEntry that = (TransactionEntry) o;
            return trxId == that.trxId &&
                    type == that.type &&
                    state == that.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(trxId, type, state);
        }

        @Override
        public String toString() {
            return "TransactionEntry{" +
                    "trxId=" + trxId +
                    ", type=" + type +
                    ", state=" + state +
                    '}';
        }
    }

    public static class NodeEntry<T> {
        private final MultiVersionNode<T> multiVersionNode;
        private final MultiVersionNode.VersionNode<T> versionNode;

        public NodeEntry(MultiVersionNode<T> multiVersionNode, MultiVersionNode.VersionNode<T> versionNode) {
            this.multiVersionNode = multiVersionNode;
            this.versionNode = versionNode;
        }


        public MultiVersionNode<T> getMultiVersionNode() {
            return multiVersionNode;
        }

        public MultiVersionNode.VersionNode<T> getVersionNode() {
            return versionNode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeEntry nodeEntry = (NodeEntry) o;
            return Objects.equals(multiVersionNode, nodeEntry.multiVersionNode) &&
                    Objects.equals(versionNode, nodeEntry.versionNode);
        }

        @Override
        public int hashCode() {

            return Objects.hash(multiVersionNode, versionNode);
        }

        @Override
        public String toString() {
            return "NodeEntry{" +
                    "multiVersionNode=" + multiVersionNode +
                    ", versionNode=" + versionNode +
                    '}';
        }
    }
}
