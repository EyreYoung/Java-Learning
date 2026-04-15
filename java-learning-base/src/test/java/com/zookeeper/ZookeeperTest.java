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

@Slf4j(topic = "ZooKeeper Java API测试用例")
public class ZookeeperTest {

    private static ZooKeeper zk;

    @BeforeAll
    public static void init() throws IOException, InterruptedException {
        final String host = "localhost:2181";
        CountDownLatch latch = new CountDownLatch(1);
        zk = new ZooKeeper(host, 5000, watchedEvent -> {
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
