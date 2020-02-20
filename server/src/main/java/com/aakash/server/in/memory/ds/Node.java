package com.aakash.server.in.memory.ds;

import com.aakash.server.ds.MultiVersionNode;
import com.aakash.server.ds.NodeIdProvider;
import com.aakash.server.exceptions.OperationNotPermittedException;
import com.aakash.server.services.TransactionService.TransactionEntry;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Node {

    private final NodeInfo nodeInfo;
    private final Map<String, MultiVersionNode<Node>> children;
    private final char[] id = NodeIdProvider.INSTANCE.getNewUniqueId();

    public Node(NodeInfo info) {
        this.nodeInfo = info;
        if (this.nodeInfo.getAttribute().isDir()) {
            this.children = new ConcurrentHashMap<>();
        } else {
            this.children = Collections.EMPTY_MAP;
        }
    }

    private Node(NodeInfo nodeInfo, Map<String, MultiVersionNode<Node>> children) {
        this.nodeInfo = nodeInfo;
        this.children = children;
    }

    public static Node copy(NodeInfo nodeInfo, Node node) {
        return new Node(nodeInfo, node.children);
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void free() {
        this.nodeInfo.free();
        this.children.clear();
    }

    public boolean exists(final TransactionEntry transactionEntry, final String name) {
        if (!this.children.containsKey(name)) {
            return false;
        }
        MultiVersionNode<Node> nodeMultiVersionNode = this.children.get(name);
        Node node = nodeMultiVersionNode.readCommitted(transactionEntry);
        return node != null;
    }

    /**
     * It wll allow to add the child only in following condition
     *  <ol>
     *      <li> if there is no entry in past</li>
     *      <li> if there is no trx under process for the current node</li>
     *      <li> if the last committed operation was delete</li>
     *  </ol>
     * @param transactionEntry {@link TransactionEntry}
     * @param name a non null/empty child name
     * @param newChildNodeSupplier a {@link Supplier} implementation to provide the new child node
     * @return {@link MultiVersionNode}
     * @throws OperationNotPermittedException
     */
    public MultiVersionNode<Node> addChildNode(final TransactionEntry transactionEntry, final String name, final Supplier<Node> newChildNodeSupplier) throws OperationNotPermittedException {

        MultiVersionNode<Node> multiVersionNode = this.children.computeIfAbsent(name, (k) -> new MultiVersionNode<>());

        if (multiVersionNode.getHead() == null || (!multiVersionNode.getHead().getVersion().isActive() && multiVersionNode.readLatestCommitted() == null)) {
            multiVersionNode.add(transactionEntry, newChildNodeSupplier.get());
        }
        return multiVersionNode;
    }

    /**
     * Get the last commited child node which has transaction.id <= @param transactionEntry.id
     * @param transactionEntry {@link TransactionEntry}
     * @param childNodeName a non null/empty name of the child node
     * @return {@link Optional} of {@link Node}
     */
    public Optional<Node> getChildNode(final TransactionEntry transactionEntry, String childNodeName) {
        if (this.children.containsKey(childNodeName)) {
            return Optional.ofNullable(this.children.get(childNodeName).readCommitted(transactionEntry));
        }
        return Optional.empty();
    }

    /**
     * Get commited child names
     *
     * @param transactionEntry {@link TransactionEntry}
     * @return all the child which is either not deleted or does not contain anything committed before or at @param transactionEntry
     */
    public Set<String> getChildFileNames(final TransactionEntry transactionEntry) {
        return this.children.entrySet().stream()
                .filter(entry -> entry.getValue().readCommitted(transactionEntry) != null)
                .map(e -> e.getKey()).collect(Collectors.toSet());
    }

    public boolean isEmptyDir(final TransactionEntry transactionEntry) {
        return getChildFileNames(transactionEntry).isEmpty();
    }

    /**
     * Removing an item is like adding a new entry in the {@link MultiVersionNode} with null value.
     * It internally calls computeIfAbsent(@param transactionEntry, @param childName, function which returns null)
     *
     * @param transactionEntry the write {@link TransactionEntry}
     * @param childName        name of the child dir
     * @return return the associated {@link MultiVersionNode}
     * @throws OperationNotPermittedException if there is already commit from the transaction > @param trxId
     */
    public MultiVersionNode<Node> removeChildNode(final TransactionEntry transactionEntry, final String childName) throws IOException {
        return computeIfPresent(transactionEntry, childName, (name, node) -> null);
    }

    /**
     * Add a new transaction entry if there is a pre-exisiting value.
     * @param transactionEntry {@link TransactionEntry}
     * @param name a non null/empty child name
     * @param remappingFunction {@link BiFunction} to provide new {@link Node}
     * @return {@link MultiVersionNode}
     * @throws IOException
     */
    public MultiVersionNode<Node> computeIfPresent(final TransactionEntry transactionEntry, String name, BiFunction<String, Node, Node> remappingFunction) throws IOException {
        try {
            return this.children.computeIfPresent(name, (String n, MultiVersionNode<Node> mv) -> {
                try {
                    Node committed = mv.readCommitted(transactionEntry);
                    if(committed != null) {
                        mv.add(transactionEntry, remappingFunction.apply(n, committed));
                    }
                    return mv;
                } catch (OperationNotPermittedException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new IOException(e.getCause());
        }
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
    public String toString() {
        return "Node{" +
                "nodeInfo=" + nodeInfo +
                ", children=" + this.children.keySet() +
                ", id=" + Arrays.toString(id) +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeInfo, children, id);
    }

    public String getId() {
        return new String(id);
    }
}
