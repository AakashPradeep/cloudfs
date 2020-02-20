package com.aakash.server.in.memory.ds;

import org.apache.hadoop.fs.Path;

import java.util.Objects;

public class InMemoryNodeInfo implements NodeInfo {
    public static final String EMPTY_PATH = "";
    private final NodeAttribute attribute;
    private final String vendorPathUri;

    public InMemoryNodeInfo(NodeAttribute attribute, String vendorPathUri) {
        this.attribute = attribute;
        this.vendorPathUri = vendorPathUri;
    }

    public static InMemoryNodeInfo createFileNodeInfo(NodeAttribute attribute, Path path) {
        return new InMemoryNodeInfo(attribute, path.toString());
    }

    public static InMemoryNodeInfo createDirNodeInfo(NodeAttribute attribute) {
        return new InMemoryNodeInfo(attribute, EMPTY_PATH);
    }


    @Override
    public NodeAttribute getAttribute() {
        return attribute;
    }

    @Override
    public Path getVendorCloudPath() {
        return new Path(new String(this.vendorPathUri));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryNodeInfo nodeInfo = (InMemoryNodeInfo) o;
        return Objects.equals(attribute, nodeInfo.attribute) &&
                Objects.equals(vendorPathUri, nodeInfo.vendorPathUri);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(attribute);
        result = 31 * result + Objects.hashCode(vendorPathUri);
        return result;
    }

}
