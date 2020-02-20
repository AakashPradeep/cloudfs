package com.aakash.server.in.memory.ds;

import com.aakash.server.exceptions.SerializationException;
import com.aakash.server.off.heap.ds.OffHeapNodeAttribute;
import com.aakash.server.off.heap.ds.OffHeapReaderWriter;
import org.junit.Assert;
import org.junit.Test;

public class OffHeapReaderWriterTest {

    @Test
    public void testOffHeapReadAndWrite() throws SerializationException {
        OffHeapReaderWriter offHeapReaderWriter = new OffHeapReaderWriter();
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

        OffHeapNodeAttribute.NodeAttributeCompanion companion= new OffHeapNodeAttribute.NodeAttributeCompanion();
        long memory = offHeapReaderWriter.write(companion ,inMemoryNodeAttribute);

        NodeAttribute offHeapNodeAttribute = offHeapReaderWriter.read(companion, memory);


        Assert.assertEquals(inMemoryNodeAttribute, offHeapNodeAttribute);
        Assert.assertEquals(offHeapNodeAttribute.isFile(), inMemoryNodeAttribute.isFile());
        Assert.assertEquals(offHeapNodeAttribute.getPermission(), inMemoryNodeAttribute.getPermission());
        Assert.assertEquals(offHeapNodeAttribute.getOwner(), inMemoryNodeAttribute.getOwner());
        Assert.assertEquals(offHeapNodeAttribute.getGroup(), inMemoryNodeAttribute.getGroup());
        Assert.assertEquals(offHeapNodeAttribute.getCreatedTime(), inMemoryNodeAttribute.getCreatedTime());
        Assert.assertEquals(offHeapNodeAttribute.getLastModifiedTime(), inMemoryNodeAttribute.getLastModifiedTime());
        Assert.assertEquals(offHeapNodeAttribute.getFileSize(), inMemoryNodeAttribute.getFileSize());
        Assert.assertEquals(offHeapNodeAttribute.getBlockSize(), inMemoryNodeAttribute.getBlockSize());
        Assert.assertEquals(offHeapNodeAttribute.getReplication(), inMemoryNodeAttribute.getReplication());
    }
}
