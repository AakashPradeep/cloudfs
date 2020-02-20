package com.aakash.server.off.heap.ds;

import com.aakash.server.ds.NodeAttribute;
import com.aakash.server.ds.NodeInfo;
import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.in.memory.ds.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link OffHeapNodeInfo}.
 */
public class OffHeapNodeInfoTest {

    private final NodeAttribute nodeAttribute = new NodeAttributeBuilder()
            .setIsFile(true)
            .setPermission((short) 755)
            .setOwner("test_user")
            .setGroup("test_grp")
            .setTime(System.currentTimeMillis())
            .setFileSize(Integer.MAX_VALUE)
            .setBlockSize(Integer.MAX_VALUE)
            .setReplication(3)
            .createNodeAttribute();
    private final String vendorPathUri = "s3://com.aakash/a/b";

    @Test
    public void testInBytesReadAndWriteWithCompanion() throws SerializationException {

        NodeInfo nodeInfo = new InMemoryNodeInfo(nodeAttribute, vendorPathUri);
        OffHeapNodeInfo.NodeInfoCompanion companion = new OffHeapNodeInfo.NodeInfoCompanion();
        byte[] write = companion.write(nodeInfo);

        NodeInfo nodeInfoFromBytes = companion.read(write);
        Assert.assertEquals(nodeInfo, nodeInfoFromBytes);
    }

    @Test
    public void testOffHeapNodeInfoReadAndWrite() throws SerializationException {


        NodeInfo nodeInfo = new InMemoryNodeInfo(nodeAttribute, vendorPathUri);
        OffHeapNodeInfo offHeapNodeInfo = new OffHeapNodeInfo(nodeInfo);

        Assert.assertEquals(vendorPathUri, offHeapNodeInfo.getVendorCloudPath().toString());

        NodeAttribute nodeInfoAttribute = offHeapNodeInfo.getAttribute();
        Assert.assertEquals(nodeAttribute, ((OffHeapNodeAttribute) nodeInfoAttribute).getObj());
        Assert.assertEquals(nodeInfoAttribute.isFile(), nodeAttribute.isFile());
        Assert.assertEquals(nodeInfoAttribute.getPermission(), nodeAttribute.getPermission());
        Assert.assertEquals(nodeInfoAttribute.getOwner(), nodeAttribute.getOwner());
        Assert.assertEquals(nodeInfoAttribute.getGroup(), nodeAttribute.getGroup());
        Assert.assertEquals(nodeInfoAttribute.getCreatedTime(), nodeAttribute.getCreatedTime());
        Assert.assertEquals(nodeInfoAttribute.getLastModifiedTime(), nodeAttribute.getLastModifiedTime());
        Assert.assertEquals(nodeInfoAttribute.getFileSize(), nodeAttribute.getFileSize());
        Assert.assertEquals(nodeInfoAttribute.getBlockSize(), nodeAttribute.getBlockSize());
        Assert.assertEquals(nodeInfoAttribute.getReplication(), nodeAttribute.getReplication());

    }
}
