package com.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/11
 */

@Slf4j(topic = "ZK分布式锁")
public class ZooKeeperSession {

    public static void main(String[] args) {
        ZooKeeperSession.init();
        ZooKeeperSession session = new ZooKeeperSession();
        Boolean f1 = session.acquireDistributedLock(10L);
        log.debug("f1 = {}", f1);
        session.releaseDistributedLock(10L);
        Boolean f2 = session.acquireDistributedLock(11L);
        log.debug("f2 = {}", f2);
    }

    private static CountDownLatch connectSemaphore = new CountDownLatch(1);

    private ZooKeeper zookeeper;
    private CountDownLatch latch;

    private final Long waitTime = 1000L;

    public ZooKeeperSession() {
        try {

            zookeeper = new ZooKeeper("localhost:2181", 50000, new ZooKeeperWatcher());

            try {
                connectSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("ZooKeeper Session建立...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分布式锁
     */
    public Boolean acquireDistributedLock(Long productId) {
        String path = "/product-lock-" + productId;
        try {
            zookeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            while (true) {
                try {
                    Stat stat = zookeeper.exists(path, true);
                    if (stat != null) {
                        latch = new CountDownLatch(1);
                        latch.await(waitTime, TimeUnit.MILLISECONDS);
                        latch = null;
                    }
                    zookeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    return true;
                } catch (Exception ignored) {}
            }
        }
    }

    /**
     * 释放分布式锁
     */
    public void releaseDistributedLock(Long productId) {
        String path = "/product-lock-" + productId;
        try {
            zookeeper.delete(path, -1);
            log.info("释放锁 productId[{}]", productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ZooKeeperWatcher implements Watcher {

        public void process(WatchedEvent event) {
            log.info("接收到watch中的event：{}", event.getState());

            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectSemaphore.countDown();
            }

            if (latch != null) {
                latch.countDown();
            }

        }

    }

    private static class Singleton {
        private static ZooKeeperSession instance;
        static {
            instance = new ZooKeeperSession();
        }
        public static ZooKeeperSession getInstance() {
            return instance;
        }
    }

    public static ZooKeeperSession getInstance() {
        return Singleton.getInstance();
    }

    public static void init() {
        getInstance();
    }
}
