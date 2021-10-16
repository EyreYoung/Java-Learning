package com.seu.concurrent.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/19 3:00 下午
 */
@Slf4j(topic = "信号量Semaphore学习")
public class LearnSemaphore {
    private static final int count = 0;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(50);
        final Semaphore semaphore = new Semaphore(30);

        for (int i = 0; i < 50; i++) {
            final int threadNum = 1;
            int finalI = i;
            pool.execute(()->{
                try {
                    semaphore.acquire(3);
                    test(finalI);
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
        log.debug("结束");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.debug("threadNum = " + threadNum);
        Thread.sleep(1000);
    }
}
