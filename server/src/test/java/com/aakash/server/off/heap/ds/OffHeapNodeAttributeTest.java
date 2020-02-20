package com.aakash.server.off.heap.ds;

import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.in.memory.ds.InMemoryNodeAttribute;
import com.aakash.server.in.memory.ds.NodeAttribute;
import com.aakash.server.in.memory.ds.NodeAttributeBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link OffHeapNodeAttribute}
 */
public class OffHeapNodeAttributeTest {

    @Test
    public void testOffHeapNodeAttributeWriteAndRead() throws SerializationException {
        long time = System.currentTimeMillis();
        int s = Integer.MAX_VALUE;
        InMemoryNodeAttribute inMemoryNodeAttribute = new NodeAttributeBuilder()
                .setIsFile(true)
                .setPermission((short) 755)
                .setOwner("test_user")
                .setGroup("test_grp")
                .setTime(time)
                .setFileSize(s)
                .setBlockSize(s)
                .setReplication(3)
                .createNodeAttribute();

        OffHeapNodeAttribute offHeapNodeAttribute = new OffHeapNodeAttribute(inMemoryNodeAttribute);

        NodeAttribute nodeAttribute = OffHeapReaderWriter.INSTANCE.read(new OffHeapNodeAttribute.NodeAttributeCompanion(), offHeapNodeAttribute.getMemoryAddress());

        Assert.assertEquals(inMemoryNodeAttribute, nodeAttribute);
        Assert.assertEquals(offHeapNodeAttribute.isFile(), inMemoryNodeAttribute.isFile());
        Assert.assertEquals(offHeapNodeAttribute.getPermission(), inMemoryNodeAttribute.getPermission());
        Assert.assertEquals(offHeapNodeAttribute.getOwner(), inMemoryNodeAttribute.getOwner());
        Assert.assertEquals(offHeapNodeAttribute.getGroup(), inMemoryNodeAttribute.getGroup());
        Assert.assertEquals(offHeapNodeAttribute.getCreatedTime(), inMemoryNodeAttribute.getCreatedTime());
        Assert.assertEquals(offHeapNodeAttribute.getLastModifiedTime(), inMemoryNodeAttribute.getLastModifiedTime());
        Assert.assertEquals(offHeapNodeAttribute.getFileSize(), inMemoryNodeAttribute.getFileSize());
        Assert.assertEquals(offHeapNodeAttribute.getBlockSize(), inMemoryNodeAttribute.getBlockSize());
        Assert.assertEquals(offHeapNodeAttribute.getReplication(), inMemoryNodeAttribute.getReplication());


        OffHeapNodeAttribute offHeapNodeAttribute2 = new OffHeapNodeAttribute(inMemoryNodeAttribute);
        Assert.assertEquals(offHeapNodeAttribute, offHeapNodeAttribute2);
        OffHeapReaderWriter.INSTANCE.free(offHeapNodeAttribute.getMemoryAddress());
        OffHeapReaderWriter.INSTANCE.free(offHeapNodeAttribute2.getMemoryAddress());
    }
}
