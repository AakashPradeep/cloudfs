package com.aakash.server.services;

import com.aakash.cloudfs.protocol.proto.generated.stubs.*;
import com.aakash.server.exceptions.LockNotAcquiredException;
import com.aakash.server.in.memory.ds.DirContainer;
import com.aakash.server.in.memory.ds.Node;
import com.aakash.server.in.memory.ds.NodeAttribute;
import com.aakash.server.in.memory.ds.NodeInfo;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FileOperationService {
    private final UTCTimeProvider utcTimeProvider = new UTCTimeProvider();
    private final NamespaceContainerService namespaceContainerService;
    private final PathBasedNodeTraversal pathBasedNodeTraversal = new PathBasedNodeTraversal();
    private final PermissionChecker permissionChecker;

    public FileOperationService(NamespaceContainerService namespaceContainerService, Configuration configuration) throws InstantiationException, IllegalAccessException {
        this.namespaceContainerService = namespaceContainerService;
        this.permissionChecker = getPermissionChecker(configuration);
    }

    public ResultMsg createZeroByteFile(CreateFileReq createFileReq) throws Exception {
        Preconditions.checkNotNull(createFileReq);
        final FSPathReq fsPathReq = createFileReq.getFsPath();
        Preconditions.checkNotNull(fsPathReq);
        final String namespace = fsPathReq.getNamespace().getName();
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(fsPathReq.getPath()), "path cannot be null");

        final Path path = new Path(fsPathReq.getPath());

        if (exists(namespace, path, fsPathReq.getOwner(), fsPathReq.getGroupsList())) {
            throw new FileAlreadyExistsException("path: " + path + " already exist");
        }
        //TODO send all the group for permission validation
        LockHandler lockHandler = new LockHandler(createFileReq.getReqTimeOutInMillis());
        try {
            Optional<Node> optionalParentNode = traversePath(namespace, path.getParent(), fsPathReq.getOwner(), fsPathReq.getGroupsList(), lockHandler);
            Node parentNode = optionalParentNode.orElseThrow(() -> new FileNotFoundException("parent dir:" + path.getParent() + "does not exist"));
            if (!parentNode.getNodeInfo().getAttribute().isDir()) {
                throw new IOException("cannot createZeroByteFile a file inside another file:" + path.getParent());
            }
            final short permission = createFileReq.getPermission() != 0 ? (short) createFileReq.getPermission() : parentNode.getNodeInfo().getAttribute().getPermission();
            final String owner = fsPathReq.getOwner() != null ? fsPathReq.getOwner() : parentNode.getNodeInfo().getAttribute().getOwner();
            final String group = parentNode.getNodeInfo().getAttribute().getGroup();

            Optional<Node> childNode = parentNode.getChildNode(path.getName());
            if (childNode.isPresent()) {
                throw new FileAlreadyExistsException("path already exist:" + path);
            }

            Optional<Node> resultNode = Optional.ofNullable(parentNode.addChildNode(path.getName(), () -> {
                final NodeAttribute nodeAttribute = NodeAttribute.createFileNodeAttribute(permission, owner, group, utcTimeProvider.currentEpochTime());
                nodeAttribute.setBlockSize(createFileReq.getBlockSize());
                nodeAttribute.setFileSize(0);
                nodeAttribute.setReplication(createFileReq.getReplication());
                NodeInfo fileNodeInfo = Optional.ofNullable(createFileReq.getVendorPath())
                        .map(p -> new Path(p))
                        .map(p -> NodeInfo.createFileNodeInfo(nodeAttribute, p))
                        .orElse(NodeInfo.createDirNodeInfo(nodeAttribute));
                Node fileNode = new Node(fileNodeInfo);
                return fileNode;
            }));



            return ResultMsg.newBuilder().setSuccess(resultNode.isPresent()).build();
        } finally {
            lockHandler.unlock();
        }
    }

    public ResultMsg createOnUploadCompletion(CreateFileReq createFileReq) throws Exception {
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
        final LockHandler lockHandler = new LockHandler(createFileReq.getReqTimeOutInMillis());
        try {

            Optional<Node> optionalParentNode = this.pathBasedNodeTraversal.traverse
                    (path.getParent(), rootNode, new PermissionChecker.DoNothingPermissionCheckerImpl(),
                            fsPathReq.getOwner(), fsPathReq.getGroupsList(), lockHandler);

            Node parentNode = optionalParentNode.orElseThrow(() -> new FileNotFoundException("parent dir:" + path.getParent() + "does not exist"));
            if (!parentNode.getNodeInfo().getAttribute().isDir()) {
                throw new IOException("cannot create a file inside another file:" + path.getParent());
            }

            final Path vendorPath = new Path(createFileReq.getVendorPath());
            Node computeIfPresent = parentNode.computeIfPresent(path.getName(), (s, node) -> {
                if (node.getNodeInfo().getVendorCloudPath().equals(vendorPath)) {
                    final NodeAttribute nodeAttribute = node.getNodeInfo().getAttribute();
                    nodeAttribute.setFileSize(createFileReq.getFileSize());
                    return Node.copy(NodeInfo.createFileNodeInfo(nodeAttribute, vendorPath), node);
                }
                return node;
            });

            final short permission = createFileReq.getPermission() != 0 ? (short) createFileReq.getPermission() : parentNode.getNodeInfo().getAttribute().getPermission();
            final String owner = fsPathReq.getOwner() != null ? fsPathReq.getOwner() : parentNode.getNodeInfo().getAttribute().getOwner();
            final String group = parentNode.getNodeInfo().getAttribute().getGroup();
            Node node = Optional.ofNullable(computeIfPresent).orElseGet(() -> {
                try {
                    return parentNode.addChildNode(path.getName(), () -> {

                        final NodeAttribute nodeAttribute = NodeAttribute.createFileNodeAttribute(permission, owner, group, utcTimeProvider.currentEpochTime());
                        nodeAttribute.setBlockSize(createFileReq.getBlockSize());
                        nodeAttribute.setFileSize(createFileReq.getFileSize());
                        nodeAttribute.setReplication(createFileReq.getReplication());
                        final NodeInfo fileNodeInfo = NodeInfo.createFileNodeInfo(nodeAttribute, vendorPath);
                        return new Node(fileNodeInfo);
                    });
                } catch (LockNotAcquiredException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });


            final Optional<Node> resultNode = Optional.ofNullable(node);

            if (resultNode.isPresent()) {
                if (!node.getNodeInfo().getVendorCloudPath().equals(vendorPath)) {
                    throw new FileAlreadyExistsException("path already exist:" + path);
                }
            } else {
                throw new IOException("not able to create the file with path:" + path);
            }

            return ResultMsg.newBuilder().setSuccess(resultNode.isPresent()).build();
        } finally {
            lockHandler.unlock();
        }
    }

    public ResultMsg mkdirs(final DirReq dirReq) throws Exception {
        Preconditions.checkNotNull(dirReq);
        final FSPathReq fsPathReq = dirReq.getFsPath();
        Preconditions.checkNotNull(fsPathReq);
        mkdirs(fsPathReq.getNamespace().getName(), new Path(fsPathReq.getPath()), fsPathReq.getOwner(), fsPathReq.getGroupsList(), (short) dirReq.getPermission()
                , dirReq.getReqTimeOutInMillis());
        return ResultMsg.newBuilder().setSuccess(true).build();
    }

    private void mkdirs(String namespace, Path path, String owner, List<String> groups, short permission, long reqTimeoutInMillis) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkNotNull(path, "path cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(owner), "owner cannot be null");
        Preconditions.checkArgument(groups != null || !groups.isEmpty(), "group cannot be null or empty");
        final DirContainer dirContainer = namespaceContainerService.getDirContainer(namespace);
        String[] names = path.toUri().getPath().split("/");

        long createdTime = utcTimeProvider.currentEpochTime();
        String rootNodeName = dirContainer.name;
        LockHandler lockHandler = new LockHandler(reqTimeoutInMillis);
        try {
            Node rootNode = dirContainer.getRootNode();
            for (int i = 0; i < names.length; i++) {
                final String childDirName = names[i];
                if (childDirName.isEmpty()) {
                    continue;
                }
                lockHandler.visit(rootNode);
                final NodeInfo nodeInfo = rootNode.getNodeInfo();
                //TODO check permission since we are creating dir inside another dir we may need write permission also
                this.permissionChecker.check(nodeInfo.getAttribute().getPermission(), owner, groups, false, FsAction.READ_EXECUTE);
                if (nodeInfo.getAttribute().isFile()) {
                    throw new IOException(rootNodeName + " is a file so it cannot contain another file or dir");
                }
                final NodeAttribute attribute = rootNode.getNodeInfo().getAttribute();
                final Supplier<Node> nodeSupplier = () ->
                {
                    NodeAttribute dirNodeAttribute = NodeAttribute.createDirNodeAttribute(permission, owner, attribute.getGroup(), createdTime);
                    dirNodeAttribute.setPermission(permission > 0 ? permission : attribute.getPermission());
                    return new Node(NodeInfo.createDirNodeInfo(dirNodeAttribute));
                };

                rootNode = rootNode.getChildNode(childDirName).orElse(rootNode.addChildNode(childDirName, nodeSupplier));
                rootNodeName = childDirName;
            }
        } finally {
            lockHandler.unlock();
        }
    }

    public CFSFileStatus getFileStatus(FSPathReq fsPathReq) throws Exception {
        final Path path = new Path(fsPathReq.getPath());
        final Node node = getFileAttribute(fsPathReq.getNamespace().getName(), path, fsPathReq.getOwner(), fsPathReq.getGroupsList());

        try {
            return new NodeToCFSFileStatusMapper().apply(node, path);
        } catch (RuntimeException e) {
            throw new IOException(e);
        }
    }

    private Node getFileAttribute(String namespace, Path path, String owner, List<String> groups) throws Exception {
        Optional<Node> optionalNode = traversePath(namespace, path, owner, groups, Visitor.DO_NOTHING_VISITOR);

        return optionalNode.orElseThrow(() -> new FileNotFoundException("not found path: " + path));

    }

    public CFSFileStatusMap getFileStatusMap(final FSPathReq fsPathReq) throws Exception {
        final Map<Path, Node> fileAttributes = getFileAttributes(fsPathReq.getNamespace().getName(), new Path(fsPathReq.getPath()),
                fsPathReq.getOwner(), fsPathReq.getGroupsList());

        final NodeToCFSFileStatusMapper mapper = new NodeToCFSFileStatusMapper();
        final Map<String, CFSFileStatus> statusMap = fileAttributes.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), e -> mapper.apply(e.getValue(), e.getKey())
                ));
        return CFSFileStatusMap.newBuilder().putAllCfsPathToFileStatusMap(statusMap).build();
    }

    public Map<Path, Node> getFileAttributes(String namespace, final Path path, String owner, List<String> groups) throws Exception {
        Optional<Node> optionalNode = traversePath(namespace, path, owner, groups, Visitor.DO_NOTHING_VISITOR);
        final Node rootNode = optionalNode.orElseThrow(() -> new FileNotFoundException("not found path: " + path));

        final NodeInfo nodeInfo = rootNode.getNodeInfo();
        if (nodeInfo.getAttribute().isFile()) {
            return Collections.singletonMap(path, rootNode);
        }

        Map<Path, Node> result = new HashMap<>();
        rootNode.getChildFileNames().stream().forEach(name -> {
            Optional<Node> optionalChildNode = rootNode.getChildNode(name);
            optionalChildNode.ifPresent(node -> result.putIfAbsent(new Path(path, name), node));
        });
        return result;
    }

    public ExistMsg exists(FSPathReq fsPathReq) throws Exception {
        boolean result = exists(fsPathReq.getNamespace().getName(), new Path(fsPathReq.getPath()),
                fsPathReq.getOwner(), fsPathReq.getGroupsList());
        return ExistMsg.newBuilder().setExists(result).build();
    }

    private boolean exists(String namespace, Path path, String owner, List<String> groups) throws Exception {
        Optional<Node> optionalNode = traversePath(namespace, path, owner, groups, Visitor.DO_NOTHING_VISITOR);
        return optionalNode.isPresent();
    }

    private Optional<Node> traversePath(String namespace, Path path, String owner, List<String> groups, Visitor<Boolean> visitor) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null");
        Preconditions.checkNotNull(path, "path cannot be null");

        final DirContainer dirContainer = namespaceContainerService.getDirContainer(namespace);
        Node rootNode = dirContainer.getRootNode();

        return this.pathBasedNodeTraversal.traverse(path, rootNode, this.permissionChecker, owner, groups, visitor);
    }

    protected PermissionChecker getPermissionChecker(Configuration configuration) throws IllegalAccessException, InstantiationException {
        Class<PermissionChecker> aClass = (Class<PermissionChecker>) configuration.getClass("permission.check.class", PermissionChecker.DefaultPermissionCheckerImpl.class);
        return aClass.newInstance();
    }


    public RenameMsg rename(RenameFSPath renameFSPath) throws Exception {
        boolean result = rename(renameFSPath.getNamespace(), new Path(renameFSPath.getSrcPath()),
                new Path(renameFSPath.getDstPath()),
                renameFSPath.getOwner(),
                renameFSPath.getGroupList(), renameFSPath.getReqTimeOutInMillis());
        return RenameMsg.newBuilder().setSuccess(result).build();
    }

    private boolean rename(String namespace, Path src, Path dst, String owner, List<String> groups, long reqTimeOutInMillis) throws Exception {
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

        LockHandler srcPathlockHandler = new LockHandler(reqTimeOutInMillis);
        LockHandler dstPathlockHandler = new LockHandler(reqTimeOutInMillis);
        try {
            Optional<Node> optionSourceParentNode = traversePath(namespace, src.getParent(), owner, groups, srcPathlockHandler);
            final Node sourceParentNode = optionSourceParentNode.orElseThrow(() -> new FileNotFoundException("not found path:" + src.getParent()));

            Node srcNode = sourceParentNode.getChildNode(src.getName()).orElseThrow(() -> new FileNotFoundException("not found path:" + src));


            final Path dstParentPath = dst.getParent();
            Optional<Node> optionalDestParentNode = traversePath(namespace, dstParentPath, owner, groups, dstPathlockHandler);
            final Node destParentNode = optionalDestParentNode.orElseThrow(() -> new FileNotFoundException("rename " +
                    "destination parent " + dstParentPath + " not found."));

            if (destParentNode.getNodeInfo().getAttribute().isFile()) {
                throw new ParentNotDirectoryException(dst + "rename destination parent" + dstParentPath + " is a file");
            }

            final Optional<Node> optionalDestNode = destParentNode.getChildNode(dst.getName());

            final Lock sourceParentNodeWriteLock = sourceParentNode.getReadWriteLock().writeLock();
            try {
                sourceParentNodeWriteLock.tryLock(reqTimeOutInMillis, TimeUnit.MILLISECONDS);

                if (optionalDestNode.isPresent()) {
                    final Node dstNode = optionalDestNode.get();
                    Lock writeLock = destParentNode.getReadWriteLock().writeLock();
                    try {
                        writeLock.tryLock(reqTimeOutInMillis, TimeUnit.MILLISECONDS);

                        if (srcNode.getNodeInfo().getAttribute().isDir() != dstNode.getNodeInfo().getAttribute().isDir()) {
                            throw new IOException("Source " + src + " Destination " + dst + " both should be either file or directory");
                        }

                        if (dstNode.getNodeInfo().getAttribute().isFile()) {
                            throw new FileAlreadyExistsException("rename destination " + dst + " already exists.");
                        }

                        if (!dstNode.isEmptyDir()) {
                            throw new IOException("rename cannot overwrite non empty destination directory " + dst);
                        }

                        if (destParentNode.computeIfPresent(dst.getName(), (s, dstNode1) -> srcNode) == srcNode) {
                            writeLock.unlock();
                            sourceParentNode.removeChildNode(src.getName());
                        }

                    } finally {
                        writeLock.unlock();
                    }
                } else {
                    if (destParentNode.addChildNode(dst.getName(), () -> srcNode) == srcNode) {
                        sourceParentNode.removeChildNode(src.getName());
                        return true;
                    }
                }
            } finally {
                sourceParentNodeWriteLock.unlock();
            }
        } finally {
            srcPathlockHandler.unlock();
            dstPathlockHandler.unlock();
        }
        return false;
    }

    public ResultMsg delete(DeleteFSReq deleteFSReq) throws Exception {
        FSPathReq fsPathReq = deleteFSReq.getFsPathReq();
        boolean result = delete(fsPathReq.getNamespace().getName(), new Path(fsPathReq.getPath()),
                deleteFSReq.getRecursive(), fsPathReq.getOwner(),
                fsPathReq.getGroupsList(), deleteFSReq.getReqTimeOutInMillis());
        return ResultMsg.newBuilder().setSuccess(result).build();
    }

    private boolean delete(String namespace, Path path, boolean recursive, String owner, List<String> groups, long reqTimeOutInMillis) throws Exception {
        if (path.isRoot()) {
            throw new IOException("cannot delete the root");
        }

        LockHandler lockHandler = new LockHandler(reqTimeOutInMillis);
        try {
            Optional<Node> optionalParentNode = traversePath(namespace, path.getParent(), owner, groups, lockHandler);
            Node parentNode = optionalParentNode.orElseThrow(() -> new FileNotFoundException("not found path:" + path.getParent()));
            Node node = parentNode.getChildNode(path.getName()).orElseThrow(() -> new FileNotFoundException("not found path:" + path));

            if (node.getNodeInfo().getAttribute().isDir()) {

                Lock nodeWriteLock = node.getReadWriteLock().writeLock();
                if (!nodeWriteLock.tryLock(reqTimeOutInMillis, TimeUnit.MILLISECONDS)) {
                    return false;
                }
                try {
                    if (!node.isEmptyDir() && !recursive) {
                        throw new IOException("cannot delete path:" + path + " as its not empty");
                    }
                    node.getNodeInfo().markForDelete();
                    parentNode.removeChildNode(path.getName());
                } finally {
                    nodeWriteLock.unlock();
                }
            }
            try {
                this.namespaceContainerService.getTrashQueue(namespace).add(node, path);
            } catch (Exception e) {
                node.getNodeInfo().markComplete();
                throw new IOException(e);
            }
            return true;
        } finally {
            lockHandler.unlock();
        }
    }

    public static class PathBasedNodeTraversal {
        public Optional<Node> traverse(Path path, Node rootNode, PermissionChecker permissionChecker, String owner, List<String> groups, Visitor<Boolean> visitor) throws Exception {
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

                if (!rootNode.exists(childDirName)) {
                    return Optional.empty();
                }
                Optional<Node> childNode = rootNode.getChildNode(childDirName);
                if (!childNode.isPresent()) {
                    return Optional.empty();
                }
                rootNode = childNode.get();
            }

            return Optional.ofNullable(rootNode);
        }
    }
}
