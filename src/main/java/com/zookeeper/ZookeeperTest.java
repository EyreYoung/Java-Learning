package com.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/28
 */

@Slf4j(topic = "ZooKeeper Java API测试用例")
public class ZookeeperTest {

    private static ZooKeeper zk;

    @BeforeAll
    public static void init() throws IOException, InterruptedException {
        final String HOST = "localhost:2181";
        CountDownLatch latch = new CountDownLatch(1);
        zk = new ZooKeeper(HOST, 5000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                latch.countDown();
            }
        });
        latch.await();
    }

    @AfterAll
    public static void destroy() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }

    @Test
    public void getStat() {
        ZooKeeper.States states = zk.getState();
        Assertions.assertTrue(states.isAlive());
    }

}
