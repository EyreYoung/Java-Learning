package com.seu.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/19 2:09 下午
 */
@Slf4j
public class LearnLock {
    public static Integer count = 0;

    public static void main(String[] args) {
        Object o = new Object();
        Lock lock = new ReentrantLock(true);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                for (int j = 0; j < 50; j++) {
                    while (true) {
                        try {
                            if (lock.tryLock(1, TimeUnit.SECONDS)) break;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    count++;
                    log.debug(count.toString());
                    lock.unlock();
                }
            }).start();
        }
        log.debug(count.toString());
    }
}
