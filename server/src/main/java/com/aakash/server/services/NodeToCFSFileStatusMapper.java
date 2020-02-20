package com.aakash.server.services;

import com.aakash.cloudfs.protocol.proto.generated.stubs.Attribute;
import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus;
import com.aakash.server.ds.NodeAttribute;
import com.aakash.server.ds.Node;
import com.aakash.server.ds.NodeInfo;
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
                //TODO fix it with last access time
                .setLastAccessedTime(attribute.getLastModifiedTime())
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
