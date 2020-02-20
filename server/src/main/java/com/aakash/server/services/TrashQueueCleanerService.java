package com.aakash.server.services;

import com.aakash.bean.NamespaceInfo;
import com.aakash.server.in.memory.ds.DirContainer;
import com.aakash.server.ds.Node;
import com.aakash.server.in.memory.ds.TrashQueue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

//invoke it from a scheduled at fixed rate service , one namespace trash queue should be consumed
//by one thread only
public class TrashQueueCleanerService {
    private final TrashQueue trashQueue;
    private final NamespaceInfo namespaceInfo;
    private final AtomicBoolean isStopped = new AtomicBoolean(false);
    private final FileSystem fileSystem;
    private final FileOperationService.PathBasedNodeTraversal pathBasedNodeTraversal = new FileOperationService.PathBasedNodeTraversal();
    private final PermissionChecker doNothingPermissionChecker = new PermissionChecker.DoNothingPermissionCheckerImpl();

    public TrashQueueCleanerService(TrashQueue trashQueue, NamespaceInfo namespaceInfo, Configuration configuration) throws URISyntaxException, IOException {
        this.trashQueue = trashQueue;
        this.namespaceInfo = namespaceInfo;
        this.fileSystem = FileSystem.get(new URI(this.namespaceInfo.getCloudVendorURI()), configuration);
    }

    public void stop() {
        isStopped.compareAndSet(false, true);
    }

    public void clean() throws Exception {
        while (trashQueue.isNotEmpty() && !isStopped.get()) {
            synchronized (this.trashQueue) {
                final long startTime = this.trashQueue.utcTimeProvider.currentEpochTime();
                TrashQueue.TrashNode peek = this.trashQueue.peek();
                long expiredTimeSinceNodeCreation = peek.getUpdateTime() - startTime;
                final TransactionService.TransactionEntry trxId = ServiceProvider.getSingeltonInstance().getTransactionService().newWriteTrxId();
                if (expiredTimeSinceNodeCreation >= this.namespaceInfo.getTrashTTLInMillis()) {
                    deleteNode(trxId, peek.getNode(), Optional.empty(), peek.getPath().getName());
                }

                final DirContainer dirContainer = ServiceProvider.getSingeltonInstance().getNamespaceContainerService()
                        .getDirContainer(this.namespaceInfo.getNamespace());

                Optional<Node> optionalParentNode = this.pathBasedNodeTraversal.traverse(trxId,
                        peek.getPath().getParent(), dirContainer.getRootNode(), this.doNothingPermissionChecker, null,
                        Collections.emptyList(), Visitor.DO_NOTHING_VISITOR);
//                optionalParentNode.ifPresent(p -> p.removeChildNode(peek.getPath().getName()));

                this.trashQueue.poll();
            }
        }
    }

    private void deleteNode(TransactionService.TransactionEntry trxId, Node node, Optional<Node> parentNode, String name) throws IOException {
        if (node.getNodeInfo().getAttribute().isFile()) {
            retry(() -> deleteFile(node), 3);

        } else if (!node.isEmptyDir(trxId)) {

            for (String childNodeName : node.getChildFileNames(trxId)) {
                Optional<Node> optionalNode = node.getChildNode(trxId, childNodeName);
                if (optionalNode.isPresent()) {
                    Node childNode = optionalNode.get();
                    deleteNode(ServiceProvider.getSingeltonInstance().getTransactionService().newWriteTrxId(), childNode, Optional.of(node), childNodeName);
                }
            }
        }

        if (parentNode.isPresent()) {
            parentNode.get().removeChildNode(trxId, name);
        }
    }

    private boolean deleteFile(Node node) throws IOException {
        return this.fileSystem.delete(node.getNodeInfo().getVendorCloudPath(), true);
    }


    private boolean retry(Callable<Boolean> callable, final int maxRetry) {
        for (int count = 1; count <= maxRetry; count++) {
            try {
                return callable.call();
            } catch (Exception e) {
                //log
                final long duration = Double.valueOf(Math.pow(2.0, count)).longValue();
                try {
                    Thread.sleep(TimeUnit.MILLISECONDS.toMillis(duration));
                } catch (InterruptedException e1) {
                    throw new RuntimeException("interrupted", e1);
                }
            }
        }
        return false;
    }

}
