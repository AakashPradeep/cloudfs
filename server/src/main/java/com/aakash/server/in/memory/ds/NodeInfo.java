package com.aakash.server.in.memory.ds;

import org.apache.hadoop.fs.Path;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class NodeInfo {
    private static final Map<Short, Status> shortValueToStatusMap = Arrays.stream(Status.values())
            .collect(Collectors.toMap(s -> (short) s.val, s -> s));
    private final NodeAttribute attribute;
    private final char[] vendorPathUri;
    private short status;

    NodeInfo(NodeAttribute attribute, Status status, char[] vendorPathUri) {
        this.attribute = attribute;
        this.vendorPathUri = vendorPathUri;
        this.status = status.val;
    }

    public static NodeInfo createFileNodeInfo(NodeAttribute attribute) {
        return new NodeInfo(attribute, Status.UNDER_WRITING, new char[0]);
    }

    public static NodeInfo createFileNodeInfo(NodeAttribute attribute, Path path) {
        return new NodeInfo(attribute, Status.COMPLETED, path.toString().toCharArray());
    }

    public static NodeInfo createDirNodeInfo(NodeAttribute attribute) {
        return new NodeInfo(attribute, Status.COMPLETED, new char[0]);
    }

    public void markComplete() {
        status = Status.COMPLETED.val;
    }

    public void markForDelete() {
        status = Status.UNDER_DELETE.val;
    }

    public void markDeleted() {
        status = Status.DELETED.val;
    }

    public NodeAttribute getAttribute() {
        return attribute;
    }

    public Path getVendorCloudPath() {
        return new Path(new String(this.vendorPathUri));
    }

    public Status getStatus() {
        return shortValueToStatusMap.get(this.status);
    }

    public enum Status {
        UNDER_WRITING(1), COMPLETED(2), UNDER_DELETE(3), DELETED(4);
        private final short val;

        Status(int val) {
            this.val = (short) val;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeInfo nodeInfo = (NodeInfo) o;
        return status == nodeInfo.status &&
                Objects.equals(attribute, nodeInfo.attribute) &&
                Arrays.equals(vendorPathUri, nodeInfo.vendorPathUri);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(attribute, status);
        result = 31 * result + Arrays.hashCode(vendorPathUri);
        return result;
    }
}
