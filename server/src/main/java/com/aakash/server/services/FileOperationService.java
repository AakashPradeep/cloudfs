package com.aakash.server.services;

import com.aakash.cloudfs.protocol.proto.generated.stubs.*;
import com.aakash.in.memory.ds.LockService;
import com.aakash.server.ds.MultiVersionNode;
import com.aakash.server.exceptions.OperationNotPermittedException;
import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.in.memory.ds.*;
import com.aakash.server.services.TransactionService.TransactionEntry;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileAlreadyExistsException;
import org.apache.hadoop.fs.ParentNotDirectoryException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FileOperationService {
    private final UTCTimeProvider utcTimeProvider = new UTCTimeProvider();
    private final NamespaceContainerService namespaceContainerService;
    private final PathBasedNodeTraversal pathBasedNodeTraversal = new PathBasedNodeTraversal();
    private final PermissionChecker permissionChecker;
    private final StorageService storageService;

    public FileOperationService(NamespaceContainerService namespaceContainerService, StorageService storageService, Configuration configuration) throws InstantiationException, IllegalAccessException {
        this.namespaceContainerService = namespaceContainerService;
        this.storageService = storageService;
        this.permissionChecker = getPermissionChecker(configuration);
    }

    public ResultMsg createZeroByteFile(final TransactionEntry transactionEntry, CreateFileReq createFileReq) throws Exception {
        Preconditions.checkArgument(transactionEntry.getTrxId() >= 0, "Transaction Id should not be <= 0");
        Preconditions.checkNotNull(createFileReq);
        final FSPathReq fsPathReq = createFileReq.getFsPath();
        Preconditions.checkNotNull(fsPathReq);
        final String namespace = fsPathReq.getNamespace().getName();
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(fsPathReq.getPath()), "path cannot be null");

        final Path path = new Path(fsPathReq.getPath());

        final LockService lockService = this.namespaceContainerService.getLockService(namespace);
        try {
            lockService.lock(path.toUri().getPath());

            if (exists(transactionEntry, namespace, path, fsPathReq.getOwner(), fsPathReq.getGroupsList())) {
                throw new FileAlreadyExistsException("path: " + path + " already exist");
            }
            //TODO send all the group for permission validation
            Optional<Node> optionalParentNode = traversePath(transactionEntry, namespace, path.getParent(), fsPathReq.getOwner(), fsPathReq.getGroupsList(), Visitor.DO_NOTHING_VISITOR);
            Node parentNode = optionalParentNode.orElseThrow(() -> new FileNotFoundException("parent dir:" + path.getParent() + "does not exist"));
            if (!parentNode.getNodeInfo().getAttribute().isDir()) {
                throw new IOException("cannot createZeroByteFile a file inside another file:" + path.getParent());
            }
            final short permission = createFileReq.getPermission() != 0 ? (short) createFileReq.getPermission() : parentNode.getNodeInfo().getAttribute().getPermission();
            final String owner = fsPathReq.getOwner() != null ? fsPathReq.getOwner() : parentNode.getNodeInfo().getAttribute().getOwner();
            final String group = parentNode.getNodeInfo().getAttribute().getGroup();

            Optional<Node> childNode = parentNode.getChildNode(transactionEntry, path.getName());
            if (childNode.isPresent()) {
                throw new FileAlreadyExistsException("path already exist:" + path);
            }
            try {

                Optional<MultiVersionNode<Node>> optionalMultiVersionNode = Optional.ofNullable(parentNode.addChildNode(transactionEntry, path.getName(), () -> {
                    final NodeAttribute inMemoryNodeAttribute = new NodeAttributeBuilder()
                            .setIsFile(true)
                            .setBlockSize(createFileReq.getBlockSize())
                            .setFileSize(0l)
                            .setPermission(permission)
                            .setGroup(group)
                            .setOwner(owner)
                            .setTime(utcTimeProvider.currentEpochTime())
                            .setReplication(createFileReq.getReplication()).createNodeAttribute();

                    NodeInfo fileNodeInfo = Optional.ofNullable(createFileReq.getVendorPath())
                            .map(p -> new Path(p))
                            .map(p -> InMemoryNodeInfo.createFileNodeInfo(inMemoryNodeAttribute, p))
                            .orElse(InMemoryNodeInfo.createDirNodeInfo(inMemoryNodeAttribute));
                    try {
                        NodeInfo nodeInfo = this.storageService.new NodeInfoFactory().getNodeInfo(fileNodeInfo);
                        Node fileNode = new Node(nodeInfo);
                        return fileNode;
                    } catch (SerializationException e) {
                        throw new RuntimeException(e);
                    }
                }));

                if (optionalMultiVersionNode.isPresent()) {
                    MultiVersionNode.VersionNode<Node> node = optionalMultiVersionNode.get().get(transactionEntry);
                    if (!node.getVersion().equals(transactionEntry)) {
                        throw new FileAlreadyExistsException("path already exist:" + path);
                    }
                }

                return ResultMsg.newBuilder().setSuccess(optionalMultiVersionNode.isPresent()).build();
            } catch (RuntimeException re) {
                throw new IOException(re.getCause());
            }
        } finally {
            lockService.unlock(path.toUri().getPath());
        }
    }

    public ResultMsg createOnUploadCompletion(final TransactionEntry transactionEntry, CreateFileReq createFileReq) throws Exception {
        Preconditions.checkArgument(transactionEntry.getTrxId() >= 0, "Transaction Id should not be <= 0");
        Preconditions.checkNotNull(createFileReq);
        final FSPathReq fsPathReq = createFileReq.getFsPath();
        Preconditions.checkNotNull(fsPathReq);
        final String namespace = fsPathReq.getNamespace().getName();
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(fsPathReq.getPath()), "path cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(createFileReq.getVendorPath()), "vendor path cannot be null");
        Preconditions.checkArgument(createFileReq.getFileSize() >= 0, "file size cannot be less than 0");

        final Path path = new Path(fsPathReq.getPath());

        final DirContainer dirContainer = this.namespaceContainerService.getDirContainer(namespace);
        final Node rootNode = dirContainer.getRootNode();

        final LockService lockService = this.namespaceContainerService.getLockService(namespace);
        try {

            lockService.lock(path.toUri().getPath());

            Optional<Node> optionalParentNode = this.pathBasedNodeTraversal.traverse
                    (transactionEntry, path.getParent(), rootNode,
                            new PermissionChecker.DoNothingPermissionCheckerImpl(), fsPathReq.getOwner(), fsPathReq.getGroupsList(), Visitor.DO_NOTHING_VISITOR);

            Node parentNode = optionalParentNode.orElseThrow(() -> new FileNotFoundException("parent dir:" + path.getParent() + "does not exist"));
            if (!parentNode.getNodeInfo().getAttribute().isDir()) {
                throw new IOException("cannot create a file inside another file:" + path.getParent());
            }

            final Path vendorPath = new Path(createFileReq.getVendorPath());


            try {
                Optional<MultiVersionNode<Node>> optionalMultiVersionInfo = Optional.ofNullable(parentNode.computeIfPresent(transactionEntry, path.getName(), (s, node) -> {
                    if (node.getNodeInfo().getVendorCloudPath().equals(vendorPath)) {
                        final NodeAttribute nodeAttribute = node.getNodeInfo().getAttribute();
                        NodeAttribute newNodeAttribute = new NodeAttributeBuilder()
                                .setIsFile(nodeAttribute.isFile())
                                .setPermission(nodeAttribute.getPermission())
                                .setOwner(nodeAttribute.getOwner())
                                .setGroup(nodeAttribute.getGroup())
                                .setTime(nodeAttribute.getCreatedTime())
                                .setFileSize(createFileReq.getFileSize())
                                .setBlockSize(createFileReq.getBlockSize())
                                .setReplication(nodeAttribute.getReplication())
                                .createNodeAttribute();
                        NodeInfo fileNodeInfo = InMemoryNodeInfo.createFileNodeInfo(newNodeAttribute, vendorPath);
                        try {
                            NodeInfo nodeInfo = this.storageService.new NodeInfoFactory().getNodeInfo(fileNodeInfo);
                            return Node.copy(nodeInfo, node);
                        } catch (SerializationException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return node;
                }));


                final short permission = createFileReq.getPermission() != 0 ? (short) createFileReq.getPermission() : parentNode.getNodeInfo().getAttribute().getPermission();
                final String owner = fsPathReq.getOwner() != null ? fsPathReq.getOwner() : parentNode.getNodeInfo().getAttribute().getOwner();
                final String group = parentNode.getNodeInfo().getAttribute().getGroup();


                MultiVersionNode<Node> nodeMultiVersionNode = optionalMultiVersionInfo.orElseGet(() -> {
                    try {
                        return parentNode.addChildNode(transactionEntry, path.getName(), () -> {

                            final NodeAttribute inMemoryNodeAttribute = new NodeAttributeBuilder()
                                    .setIsFile(true)
                                    .setBlockSize(createFileReq.getBlockSize())
                                    .setFileSize(createFileReq.getFileSize())
                                    .setPermission(permission)
                                    .setGroup(group)
                                    .setOwner(owner)
                                    .setTime(utcTimeProvider.currentEpochTime())
                                    .setReplication(createFileReq.getReplication())
                                    .createNodeAttribute();
                            final NodeInfo fileNodeInfo = InMemoryNodeInfo.createFileNodeInfo(inMemoryNodeAttribute, vendorPath);
                            try {
                                this.storageService.new NodeInfoFactory().getNodeInfo(fileNodeInfo);
                            } catch (SerializationException e) {
                                e.printStackTrace();
                            }
                            return new Node(fileNodeInfo);
                        });
                    } catch (OperationNotPermittedException e) {
                        throw new RuntimeException(e);
                    }
                });


                if (!nodeMultiVersionNode.get(transactionEntry).getVersion().equals(transactionEntry)) {
                    throw new IOException("not able to create the file with path:" + path);
                }

                return ResultMsg.newBuilder().setSuccess(true).build();

            } catch (Exception e) {
                throw new IOException(e.getCause());
            }
        } finally {
            lockService.unlock(path.toUri().getPath());
        }

    }

    public ResultMsg mkdirs(final TransactionEntry transactionEntry, final DirReq dirReq) throws Exception {
        Preconditions.checkNotNull(dirReq, "dir request cannot be null :" + dirReq);
        final FSPathReq fsPathReq = dirReq.getFsPath();
        Preconditions.checkNotNull(fsPathReq);
        final Path path = new Path(fsPathReq.getPath());
        final NamespaceName namespace = fsPathReq.getNamespace();
        final LockService lockService = this.namespaceContainerService.getLockService(namespace.getName());
        try {
            lockService.lock(path.toUri().getPath());
            mkdirs(transactionEntry, namespace.getName(), path, fsPathReq.getOwner(), fsPathReq.getGroupsList(), (short) dirReq.getPermission()
                    , dirReq.getReqTimeOutInMillis());
            return ResultMsg.newBuilder().setSuccess(true).build();
        } finally {
            lockService.unlock(path.toUri().getPath());
        }
    }

    private void mkdirs(final TransactionEntry transactionEntry, String namespace, Path path, String owner, List<String> groups, short permission, long reqTimeoutInMillis) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkNotNull(path, "path cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(owner), "owner cannot be null");
        Preconditions.checkArgument(groups != null || !groups.isEmpty(), "group cannot be null or empty");
        final DirContainer dirContainer = namespaceContainerService.getDirContainer(namespace);
        String[] names = path.toUri().getPath().split("/");

        long createdTime = utcTimeProvider.currentEpochTime();
        String rootNodeName = dirContainer.name;
        Node rootNode = dirContainer.getRootNode();
        for (int i = 0; i < names.length; i++) {
            final String childDirName = names[i];
            if (childDirName.isEmpty()) {
                continue;
            }
            final NodeInfo nodeInfo = rootNode.getNodeInfo();
            //TODO check permission since we are creating dir inside another dir we may need write permission also
            this.permissionChecker.check(nodeInfo.getAttribute().getPermission(), owner, groups, false, FsAction.READ_EXECUTE);
            if (nodeInfo.getAttribute().isFile()) {
                throw new IOException(rootNodeName + " is a file so it cannot contain another file or dir");
            }
            final NodeAttribute attribute = rootNode.getNodeInfo().getAttribute();
            final Supplier<Node> nodeSupplier = () ->
            {
                NodeAttribute dirInMemoryNodeAttribute = InMemoryNodeAttribute.createDirNodeAttribute
                        (permission > 0 ? permission : attribute.getPermission(), owner, attribute.getGroup(), createdTime);
                return new Node(InMemoryNodeInfo.createDirNodeInfo(dirInMemoryNodeAttribute));
            };

            rootNode = rootNode.getChildNode(transactionEntry, childDirName).orElse(createDirNode(transactionEntry, rootNode, childDirName, nodeSupplier));
            rootNodeName = childDirName;
        }
    }

    private Node createDirNode(TransactionEntry transactionEntry, Node rootNode, String childDirName, Supplier<Node> nodeSupplier) throws OperationNotPermittedException {
        MultiVersionNode<Node> nodeMultiVersionNode = rootNode.addChildNode(transactionEntry, childDirName, nodeSupplier);
        return nodeMultiVersionNode.read(transactionEntry);
    }

    public CFSFileStatus getFileStatus(final TransactionEntry transactionEntry, FSPathReq fsPathReq) throws Exception {
        final Path path = new Path(fsPathReq.getPath());
        final Node node = getFileAttribute(transactionEntry, fsPathReq.getNamespace().getName(), path, fsPathReq.getOwner(), fsPathReq.getGroupsList());

        try {
            return new NodeToCFSFileStatusMapper().apply(node, path);
        } catch (RuntimeException e) {
            throw new IOException(e);
        }
    }

    private Node getFileAttribute(final TransactionEntry transactionEntry, String namespace, Path path, String owner, List<String> groups) throws Exception {
        Optional<Node> optionalNode = traversePath(transactionEntry, namespace, path, owner, groups, Visitor.DO_NOTHING_VISITOR);

        return optionalNode.orElseThrow(() -> new FileNotFoundException("not found path: " + path));

    }

    public CFSFileStatusMap getFileStatusMap(final TransactionEntry transactionEntry, final FSPathReq fsPathReq) throws Exception {
        final Map<Path, Node> fileAttributes = getFileAttributes(transactionEntry, fsPathReq.getNamespace().getName(), new Path(fsPathReq.getPath()),
                fsPathReq.getOwner(), fsPathReq.getGroupsList());

        final NodeToCFSFileStatusMapper mapper = new NodeToCFSFileStatusMapper();
        final Map<String, CFSFileStatus> statusMap = fileAttributes.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), e -> mapper.apply(e.getValue(), e.getKey())
                ));
        return CFSFileStatusMap.newBuilder().putAllCfsPathToFileStatusMap(statusMap).build();
    }

    public Map<Path, Node> getFileAttributes(final TransactionEntry transactionEntry, String namespace, final Path path, String owner, List<String> groups) throws Exception {
        Optional<Node> optionalNode = traversePath(transactionEntry, namespace, path, owner, groups, Visitor.DO_NOTHING_VISITOR);
        final Node rootNode = optionalNode.orElseThrow(() -> new FileNotFoundException("not found path: " + path));

        final NodeInfo nodeInfo = rootNode.getNodeInfo();
        if (nodeInfo.getAttribute().isFile()) {
            return Collections.singletonMap(path, rootNode);
        }

        Map<Path, Node> result = new HashMap<>();
        rootNode.getChildFileNames(transactionEntry).stream().forEach(name -> {
            Optional<Node> optionalChildNode = rootNode.getChildNode(transactionEntry, name);
            optionalChildNode.ifPresent(node -> result.putIfAbsent(new Path(path, name), node));
        });
        return result;
    }

    public ExistMsg exists(final TransactionEntry transactionEntry, FSPathReq fsPathReq) throws Exception {
        boolean result = exists(transactionEntry, fsPathReq.getNamespace().getName(),
                new Path(fsPathReq.getPath()), fsPathReq.getOwner(), fsPathReq.getGroupsList());
        return ExistMsg.newBuilder().setExists(result).build();
    }

    private boolean exists(TransactionEntry transactionEntry, String namespace, Path path, String owner, List<String> groups) throws Exception {
        Optional<Node> optionalNode = traversePath(transactionEntry, namespace, path, owner, groups, Visitor.DO_NOTHING_VISITOR);
        return optionalNode.isPresent();
    }

    private Optional<Node> traversePath(final TransactionEntry transactionEntry, String namespace, Path path, String owner, List<String> groups, Visitor<Boolean> visitor) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkNotNull(path, "path cannot be null");

        final DirContainer dirContainer = this.namespaceContainerService.getDirContainer(namespace);
        Node rootNode = dirContainer.getRootNode();

        return this.pathBasedNodeTraversal.traverse(transactionEntry, path, rootNode, this.permissionChecker, owner, groups, visitor);
    }

    protected PermissionChecker getPermissionChecker(Configuration configuration) throws IllegalAccessException, InstantiationException {
        Class<PermissionChecker> aClass = (Class<PermissionChecker>) configuration.getClass("permission.check.class", PermissionChecker.DefaultPermissionCheckerImpl.class);
        return aClass.newInstance();
    }


    public RenameMsg rename(final TransactionEntry transactionEntry, RenameFSPath renameFSPath) throws Exception {
        final Path src = new Path(renameFSPath.getSrcPath());
        final Path dst = new Path(renameFSPath.getDstPath());
        String namespace = renameFSPath.getNamespace();
        final LockService lockService = this.namespaceContainerService.getLockService(namespace);
        try {
            lockService.lock(src.toUri().getPath());
            lockService.lock(dst.toUri().getPath());

            boolean result = rename(transactionEntry, namespace, src, dst, renameFSPath.getOwner(),
                    renameFSPath.getGroupList(), renameFSPath.getReqTimeOutInMillis());

            return RenameMsg.newBuilder().setSuccess(result).build();
        } finally {
            lockService.unlock(dst.toUri().getPath());
            lockService.unlock(src.toUri().getPath());
        }
    }

    private boolean rename(final TransactionEntry transactionEntry, String namespace, Path src, Path dst, String owner, List<String> groups, long reqTimeOutInMillis) throws Exception {
        if (src.isRoot() || dst.isRoot()) {
            throw new IOException("src or destination cannot be root dir");
        }

        if (src.equals(dst)) {
            return true;
        }

        //example src: /a/b/c dest: /a/b/c/d/e .. an exception cannot move c to its own child
        if (dst.toString().startsWith(src.toString())) {
            throw new IOException("source cannot be a part of parent hierarchy of destination");
        }

        Optional<Node> optionSourceParentNode = traversePath(transactionEntry, namespace, src.getParent(), owner, groups, Visitor.DO_NOTHING_VISITOR);
        final Node sourceParentNode = optionSourceParentNode.orElseThrow(() -> new FileNotFoundException("not found path:" + src.getParent()));

        Node srcNode = sourceParentNode.getChildNode(transactionEntry, src.getName()).orElseThrow(() -> new FileNotFoundException("not found path:" + src));


        final Path dstParentPath = dst.getParent();
        Optional<Node> optionalDestParentNode = traversePath(transactionEntry, namespace, dstParentPath, owner, groups, Visitor.DO_NOTHING_VISITOR);
        final Node destParentNode = optionalDestParentNode.orElseThrow(() -> new FileNotFoundException("rename " +
                "destination parent " + dstParentPath + " not found."));

        if (destParentNode.getNodeInfo().getAttribute().isFile()) {
            throw new ParentNotDirectoryException(dst + "rename destination parent" + dstParentPath + " is a file");
        }

        final Optional<Node> optionalDestNode = destParentNode.getChildNode(transactionEntry, dst.getName());

        if (optionalDestNode.isPresent()) {
            final Node dstNode = optionalDestNode.get();
            if (srcNode.getNodeInfo().getAttribute().isDir() != dstNode.getNodeInfo().getAttribute().isDir()) {
                throw new IOException("Source " + src + " Destination " + dst + " both should be either file or directory");
            }

            if (dstNode.getNodeInfo().getAttribute().isFile()) {
                throw new FileAlreadyExistsException("rename destination " + dst + " already exists.");
            }

            if (!dstNode.isEmptyDir(transactionEntry)) {
                throw new IOException("rename cannot overwrite non empty destination directory " + dst);
            }

            MultiVersionNode<Node> nodeMultiVersionNode = destParentNode.computeIfPresent(transactionEntry, dst.getName(), (s, dstNode1) -> srcNode);
            if (nodeMultiVersionNode.read(transactionEntry) == srcNode) {
                sourceParentNode.removeChildNode(transactionEntry, src.getName());
            }

        } else {
            MultiVersionNode<Node> nodeMultiVersionNode = destParentNode.addChildNode(transactionEntry, dst.getName(), () -> srcNode);
            if (nodeMultiVersionNode.read(transactionEntry) == srcNode) {
                sourceParentNode.removeChildNode(transactionEntry, src.getName());
                return true;
            }
        }
        return false;
    }

    public ResultMsg delete(final TransactionEntry transactionEntry, DeleteFSReq deleteFSReq) throws Exception {
        FSPathReq fsPathReq = deleteFSReq.getFsPathReq();
        boolean result = delete(transactionEntry, fsPathReq.getNamespace().getName(),
                new Path(fsPathReq.getPath()), deleteFSReq.getRecursive(),
                fsPathReq.getOwner(), fsPathReq.getGroupsList());
        return ResultMsg.newBuilder().setSuccess(result).build();
    }

    private boolean delete(final TransactionEntry transactionEntry, String namespace, Path path, boolean recursive, String owner, List<String> groups) throws Exception {
        if (path.isRoot()) {
            throw new IOException("cannot delete the root");
        }

        Optional<Node> optionalParentNode = traversePath(transactionEntry, namespace, path.getParent(), owner, groups, Visitor.DO_NOTHING_VISITOR);
        Node parentNode = optionalParentNode.orElseThrow(() -> new FileNotFoundException("not found path:" + path.getParent()));
        Node node = parentNode.getChildNode(transactionEntry, path.getName()).orElseThrow(() -> new FileNotFoundException("not found path:" + path));

        if (node.getNodeInfo().getAttribute().isDir()) {

            if (!node.isEmptyDir(transactionEntry) && !recursive) {
                throw new IOException("cannot delete path:" + path + " as its not empty");
            }
            parentNode.removeChildNode(transactionEntry, path.getName());
        }
        try {
            this.namespaceContainerService.getTrashQueue(namespace).add(node, path);
        } catch (Exception e) {
            throw new IOException(e);
        }
        return true;
    }

    public static class PathBasedNodeTraversal {
        public Optional<Node> traverse(final TransactionEntry transactionEntry, Path path, Node rootNode, PermissionChecker permissionChecker, String owner, List<String> groups, Visitor<Boolean> visitor) throws Exception {
            if (path.isRoot()) {
                visitor.visit(rootNode);
                return Optional.of(rootNode);
            }

            String[] names = path.toUri().getPath().split("/");
            for (int i = 0; i < names.length && rootNode != null; i++) {
                visitor.visit(rootNode);
                final NodeAttribute attribute = rootNode.getNodeInfo().getAttribute();
                permissionChecker.check(attribute.getPermission(), owner, groups, attribute.isFile(), FsAction.EXECUTE);

                final String childDirName = names[i];
                if (childDirName.isEmpty()) {
                    continue;
                }

                if (!rootNode.exists(transactionEntry, childDirName)) {
                    return Optional.empty();
                }
                Optional<Node> childNode = rootNode.getChildNode(transactionEntry, childDirName);
                if (!childNode.isPresent()) {
                    return Optional.empty();
                }
                rootNode = childNode.get();
            }

            return Optional.ofNullable(rootNode);
        }
    }
}
