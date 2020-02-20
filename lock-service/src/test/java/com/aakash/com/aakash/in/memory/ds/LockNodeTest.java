package com.aakash.com.aakash.in.memory.ds;

import com.aakash.in.memory.ds.LockNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LockNodeTest {
    @Test
    public  void testToString(){
        try {
            new LockNode("/", null).toString();
        }catch (Exception e) {
            Assert.fail("expect no exception");
        }
    }

    @Test
    public void addNode(){
        LockNode rootNode = new LockNode("/", null);
        LockNode first = rootNode.addChildren("first");

        Assert.assertEquals("first",first.getName());
        Assert.assertEquals(rootNode,first.getParent());
        Assert.assertFalse(first.getWriteLock().isLocked());
        first.lock();
        Assert.assertTrue(first.getWriteLock().isLocked());
        first.free();
        Assert.assertFalse(first.getWriteLock().isLocked());
        Assert.assertNull(rootNode.getChildNode("first"));

    }

    @Test
    public void getChildNode() {
        LockNode rootNode = new LockNode("/", null);
        LockNode first = rootNode.addChildren("first");

        Assert.assertEquals(first, rootNode.getChildNode("first"));
        Assert.assertEquals(first, rootNode.addChildren("first"));
    }

    @Test
    public void removeNode() {
        LockNode rootNode = new LockNode("/", null);
        LockNode first = rootNode.addChildren("first");
        Assert.assertEquals(first, rootNode.getChildNode("first"));

        rootNode.removeChild("first");
        Assert.assertNull(rootNode.getChildNode("first"));
    }

    @Test
    public void testLockAndUnlock() throws InterruptedException {
        LockNode rootNode = new LockNode("/", null);
        final LockNode first = rootNode.addChildren("first");
        final LockNode second = first.addChildren("second");

        second.lock();
        Assert.assertTrue(second.getWriteLock().isHeldByCurrentThread());

        final CountDownLatch firstLocked = new CountDownLatch(1);
        final CountDownLatch firstUnlock = new CountDownLatch(1);
        final CountDownLatch firstNodeDeleted = new CountDownLatch(1);

        new Thread(() -> {
            first.lock();
            firstLocked.countDown();

            try {
                firstUnlock.await(2, TimeUnit.SECONDS);
                first.free();
                firstNodeDeleted.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();

        firstLocked.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(first.getWriteLock().isLocked());
        Assert.assertFalse(first.getWriteLock().isHeldByCurrentThread());

        //should delete the second node
        second.free();
        //making sure node is not deleted
        first.free();

        Assert.assertTrue(first.getWriteLock().isLocked());
        Assert.assertEquals(first, rootNode.getChildNode("first"));
        Assert.assertNull(first.getChildNode("second"));
        firstUnlock.countDown();
        firstNodeDeleted.await(2, TimeUnit.SECONDS);
        Assert.assertNull(rootNode.getChildNode("first"));

    }
}
