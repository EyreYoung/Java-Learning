package com.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/20
 */
@Slf4j
public class ZKLock2 {

    private static String zkURL = "localhost:2181";

    private static String lockNameSpace = "/myLock";

    private static String nodeString = lockNameSpace + "/test";

    public void lockTest() {
        try {
            ZooKeeper zk = new ZooKeeper(zkURL, 6000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    log.info("监听到ZK消息: {}", event);
                    if (Event.KeeperState.SyncConnected.equals(event.getState())) {
                        log.info("ZK连接建立完成");
                    }
                }
            });
        } catch (IOException e) {
            log.error("", e);
        }
    }

    // TODO 未完成

}
