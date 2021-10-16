package com.seu.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/24 5:59 下午
 */
@Slf4j(topic = "学习死锁")
public class LearnDeadLock {
    // 创建资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    log.debug(Thread.currentThread() + " get Resource A");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug(Thread.currentThread() + " wait to get Resource B");
                    synchronized (resourceB) {
                        log.debug(Thread.currentThread() + " get resource B");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    log.debug(Thread.currentThread() + " get Resource B");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug(Thread.currentThread() + " wait to get Resource A");
                    synchronized (resourceA) {
                        log.debug(Thread.currentThread() + " get resource A");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
