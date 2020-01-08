package com.aakash.server.in.memory.ds;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * unit test for {@link NodeIdProvider}
 */
public class NodeIdProviderTest {

    @Test
    public void testUniqueId() {

        Set<String> uniqueIds = new HashSet<>();
        IntStream.range(0, 10).forEach(
                i -> uniqueIds.add(new String(NodeIdProvider.INSTANCE.getNewUniqueId()))
        );

        System.out.println(uniqueIds);
        Assert.assertEquals(uniqueIds.size(), 10);
    }
}
