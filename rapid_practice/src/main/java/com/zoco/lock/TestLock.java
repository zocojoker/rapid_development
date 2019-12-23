package com.zoco.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试lock
 *
 * @author zoco
 * @creat 2019-11-19-10:00
 */
public class TestLock {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(50);
        final int[] counter = {0};
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        int a = counter[0];
                        counter[0] = a + 1;
                    } finally {
                        lock.unlock();
                    }
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        // 主线程休眠，等待结果

        Thread.sleep(5000);
        System.out.println(counter[0]);

    }
}
