package com.aakash.com.aakash.in.memory.ds;

import com.aakash.in.memory.ds.LockService;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LockServiceTest {

    @Test
    public void testLockAndUnlock() throws InterruptedException {
        final LockService lockService = new LockService();
        final String testPath = "/a/b/c";
        Assert.assertTrue(lockService.lock(testPath));
        Assert.assertTrue(lockService.unlock(testPath));


        //test locked cannot be locked again with a timeout
        Assert.assertTrue(lockService.lock(testPath));
        boolean isLocked = lockService.lockWithTimeOut(testPath, 100l);
        Assert.assertTrue(isLocked);
        Assert.assertTrue(lockService.unlock(testPath));

        final CountDownLatch unlockedCountDownLatch = new CountDownLatch(1);
        final CountDownLatch waitForThreadDownLatch = new CountDownLatch(1);


        Thread anotherThread = new Thread(() -> {
            Assert.assertTrue(lockService.lock(testPath));
            try {
                unlockedCountDownLatch.await(2, TimeUnit.SECONDS);
                Assert.assertTrue(lockService.unlock(testPath));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                waitForThreadDownLatch.countDown();
            }

        });

        Assert.assertTrue(lockService.lock(testPath));
        anotherThread.start();
        Assert.assertTrue(lockService.unlock(testPath));
        unlockedCountDownLatch.countDown();

        waitForThreadDownLatch.await(2, TimeUnit.SECONDS);
    }
}
