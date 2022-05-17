package com.core.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁测试
 */

@Slf4j
public class DeadLock {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();

        new Thread(() -> {
            try {
                dataSource.getSourceA();
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }).start();

        new Thread(() -> {
            try {
                dataSource.getSourceB();
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }).start();

    }

}

@Slf4j
class DataSource {
    private final String sourceA = "A资源";
    private final String sourceB = "B资源";

    void getSourceA() throws InterruptedException {
        synchronized (sourceA) {
            log.info("{} 已经获取 {}", Thread.currentThread().getName(), sourceA);
            Thread.sleep(2000);
            log.info("{} 尝试获取 {}", Thread.currentThread().getName(), sourceB);
            synchronized (sourceB) {
                log.info("{} 已经获取 {}", Thread.currentThread().getName(), sourceB);
            }
        }
    }

    void getSourceB() throws InterruptedException {
        synchronized (sourceB) {
            log.info("{} 已经获取 {}", Thread.currentThread().getName(), sourceB);
            Thread.sleep(1000);
            log.info("{} 尝试获取 {}", Thread.currentThread().getName(), sourceA);
            synchronized (sourceA) {
                log.info("{} 已经获取 {}", Thread.currentThread().getName(), sourceA);
            }
        }
    }

}
