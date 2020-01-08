package com.aakash.server.services;

import com.aakash.cloudfs.protocol.proto.generated.stubs.Attribute;
import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus;
import com.aakash.server.in.memory.ds.Node;
import com.aakash.server.in.memory.ds.NodeAttribute;
import com.aakash.server.in.memory.ds.NodeInfo;
import org.apache.hadoop.fs.Path;

import java.util.function.BiFunction;

public class NodeToCFSFileStatusMapper implements BiFunction<Node, Path, CFSFileStatus> {
    @Override
    public CFSFileStatus apply(Node node, Path path) {
        final NodeInfo nodeInfo = node.getNodeInfo();
        final NodeAttribute attribute = nodeInfo.getAttribute();
        final Attribute resultAttribute = Attribute.newBuilder()
                .setBlockSize(attribute.getBlockSize())
                .setLastModifiedTime(attribute.getLastModifiedTime())
                .setLastAccessedTime(attribute.getLastAccessedTime())
                .setFileSize(attribute.getFileSize())
                .setCreatedTime(attribute.getCreatedTime())
                .setGroup(attribute.getGroup())
                .setOwner(attribute.getOwner())
                .setPermission(attribute.getPermission())
                .setReplication(attribute.getReplication())
                .setIsFile(attribute.isFile()).build();

        CFSFileStatus.Builder builder = CFSFileStatus.newBuilder();
        if (attribute.isFile()) {
            builder = builder.setVendorPath(nodeInfo.getVendorCloudPath().toString());
        }
        return builder
                .setAttribute(resultAttribute)
                .setCfsPath(path.toString()).build();
    }
}
