package com.aakash.server.off.heap.ds;

import com.aakash.server.ds.Readable;
import com.aakash.server.ds.Writable;
import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.in.memory.ds.InMemoryNodeInfo;
import com.aakash.server.in.memory.ds.NodeAttribute;
import com.aakash.server.in.memory.ds.NodeInfo;
import com.google.common.primitives.Ints;
import org.apache.commons.io.Charsets;
import org.apache.hadoop.fs.Path;

/**
 * An off Heap based implementation of {@link NodeInfo}
 */
public class OffHeapNodeInfo implements NodeInfo {
    public static final int CLOUD_VENDOR_PATH_OFFSET = 0;
    private static final NodeInfoCompanion companion = new NodeInfoCompanion();
    private final long memoryAddress;

    public OffHeapNodeInfo(NodeInfo nodeInfo) throws SerializationException {
        this.memoryAddress = OffHeapReaderWriter.INSTANCE.write(companion, nodeInfo);

        if (Long.valueOf(this.memoryAddress).compareTo(OffHeapNodeAttribute.ZERO_LONG) <= 0) {
            throw new RuntimeException("Not able to allocate off heap memory (return code:" + this.memoryAddress
                    + ") for nodeInfo:" + nodeInfo);
        }
    }

    @Override
    public void free() {
        OffHeapReaderWriter.INSTANCE.free(this.memoryAddress);
    }

    @Override
    public NodeAttribute getAttribute() {
        Integer vendorPathLength = OffHeapReaderWriter.INSTANCE.readInt(this.memoryAddress, CLOUD_VENDOR_PATH_OFFSET);
        final int attributeLengthOffset = 4 + 4 + vendorPathLength ;
        return new OffHeapNodeAttribute(this.memoryAddress+ attributeLengthOffset);
    }

    @Override
    public Path getVendorCloudPath() {
        final String pathInStr = OffHeapReaderWriter.INSTANCE.readString(this.memoryAddress, 0);
        return new Path(pathInStr);
    }


    public static class NodeInfoCompanion extends AbstractCompanion<NodeInfo> implements Writable<NodeInfo>, Readable<NodeInfo> {
        final OffHeapNodeAttribute.NodeAttributeCompanion attributeCompanion = OffHeapNodeAttribute.companion;

        @Override
        public NodeInfo read(byte[] bytes) throws SerializationException {
            final IndexWithValue<Integer> cloudVendorPathIndexWithValue = readInt(bytes, CLOUD_VENDOR_PATH_OFFSET);
            byte[] cloudVendorPathInBytes = new byte[cloudVendorPathIndexWithValue.value];
            int index = read(bytes, cloudVendorPathIndexWithValue.index + 1, cloudVendorPathInBytes);

            IndexWithValue<Integer> attributeIndexWithValue = readInt(bytes, index + 1);
            byte[] attributesInBytes = new byte[attributeIndexWithValue.value];
            read(bytes, attributeIndexWithValue.index + 1, attributesInBytes);

            final NodeAttribute inMemoryNodeAttribute = this.attributeCompanion.read(attributesInBytes);
            return new InMemoryNodeInfo(inMemoryNodeAttribute, new String(cloudVendorPathInBytes, Charsets.UTF_8));
        }

        @Override
        public byte[] write(NodeInfo obj) throws SerializationException {
            byte[] bytes = obj.getVendorCloudPath().toString().getBytes(Charsets.UTF_8);
            byte[] nodeAttributesInBytes = this.attributeCompanion.write(obj.getAttribute());

            final int totalSize = 4 * 2 + bytes.length + nodeAttributesInBytes.length;
            byte[] result = new byte[totalSize];

            int index = copy(result, CLOUD_VENDOR_PATH_OFFSET, Ints.toByteArray(bytes.length));
            index = copy(result, index + 1, bytes);
            index = copy(result, index + 1, Ints.toByteArray(nodeAttributesInBytes.length));
            copy(result, index + 1, nodeAttributesInBytes);
            return result;
        }
    }
}
