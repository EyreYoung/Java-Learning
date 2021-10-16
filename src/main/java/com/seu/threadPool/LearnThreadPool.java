package com.seu.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/17 2:12 下午
 */
@Slf4j(topic = "线程池学习")
public class LearnThreadPool {
    private static Integer count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(4, 6, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.CallerRunsPolicy());
        executor1.submit(() -> {
            log.debug("单线程打印，开始等待2000");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("单线程等待2000完毕");
        });
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
//                synchronized (count) {
                    for (int j = 0; j < 50; j++) {
                        log.debug("count = " + ++count);
                    }
//                }
            });
        }
        log.debug("开始shutdown");
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(1);
        }
        log.debug("Terminated");

        executor1.execute(new Runnable() {
            @Override
            public void run() {
                log.debug("just for fun!");
            }
        });
        AbstractOwnableSynchronizer aos  = new AbstractOwnableSynchronizer() {};
    }
}

class MyLock implements Lock {

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
