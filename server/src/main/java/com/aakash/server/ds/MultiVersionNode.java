package com.aakash.server.ds;

import com.aakash.server.exceptions.OperationNotPermittedException;
import com.aakash.server.in.memory.ds.Visitor;
import com.aakash.server.services.TransactionService.TransactionEntry;

/**
 * A multi version node to support snapshot and avoid any readCommitted and write lock. This will allow to multiple readCommitted and write
 * operation in parallel. The current implementation only allow one write operation at a time but allow multiple readCommitted
 * operation to be executed in parallel.
 * <p>
 * Its a double link list so that delete can be performed from any node.
 *
 * @param <T> The data to be store in current case its {@link Node}
 */
public class MultiVersionNode<T> {
    private VersionNode<T> head;
    private int versionHistorySize = 0;

    public synchronized void add(TransactionEntry transactionEntry, T data) throws OperationNotPermittedException {
        VersionNode<T> versionNode = new VersionNode<>(transactionEntry, data);
        if (head != null) {

            if (transactionEntry.compareTo(this.head.version) <= 0 && !this.head.getVersion().isFailed()) {
                throw new OperationNotPermittedException("Previous committed transaction:" + this.head.version
                        + " is higher than current:" + transactionEntry + ".");
            }

            versionNode.setNext(this.head);
            this.head.setPrev(versionNode);
        }
        this.head = versionNode;
        this.versionHistorySize++;
    }

    public synchronized void delete(TransactionEntry transactionEntry) throws OperationNotPermittedException {
        add(transactionEntry, null);
    }

    public VersionNode<T> getHead() {
        return this.head;
    }

    public VersionNode<T> getCommittedNodeWith(TransactionEntry transactionEntry) {
        if (this.head != null) {
            VersionNode<T> find = this.head;

            while (find != null) {
                if (find.version.isCommitted() && find.version.getTrxId() <= transactionEntry.getTrxId()) {
                    return find;
                }
                find = find.next;
            }
        }
        return null;
    }

    public T readLatestCommitted() {
        VersionNode<T> node = this.head;
        for (; node != null && !node.getVersion().isCommitted(); node = node.next) ;
        if (node != null) {
            return node.getData();
        }
        return null;
    }


    public VersionNode<T> get(TransactionEntry transactionEntry) {
        VersionNode<T> versionNode = this.head;
        for (; versionNode != null && !versionNode.getVersion().isFailed() && versionNode.getVersion().getTrxId() > transactionEntry.getTrxId();
             versionNode = versionNode.next) ;
        if (versionNode != null) {
            return versionNode;
        }
        return null;
    }

    public T read(TransactionEntry transactionEntry) {
        VersionNode<T> versionNode = get(transactionEntry);
        if (versionNode != null) {
            return versionNode.getData();
        }
        return null;
    }

    public T readCommitted(TransactionEntry transactionEntry) {
        VersionNode<T> node = getCommittedNodeWith(transactionEntry);
        if (node != null) {
            return node.getData();
        }
        return null;
    }

    public <R> R visit(Visitor<MultiVersionNode<T>, R> visitor) {
        return visitor.visit(this);
    }

    public int getVersionHistorySize() {
        return versionHistorySize;
    }

    public static class VersionNode<T> {
        private final TransactionEntry version;
        private final T data;
        private VersionNode<T> next = null;
        private VersionNode<T> prev = null;

        public VersionNode(TransactionEntry version, T data) {
            this.version = version;
            this.data = data;
        }

        public VersionNode(TransactionEntry version, T data, VersionNode<T> next, VersionNode<T> prev) {
            this.version = version;
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public TransactionEntry getVersion() {
            return version;
        }

        public T getData() {
            return data;
        }

        public VersionNode<T> getNext() {
            return next;
        }

        public VersionNode<T> setNext(VersionNode<T> next) {
            this.next = next;
            return this;
        }

        public VersionNode<T> getPrev() {
            return prev;
        }

        public VersionNode<T> setPrev(VersionNode<T> prev) {
            this.prev = prev;
            return this;
        }
    }


}
