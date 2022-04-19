package com.core.future;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/15
 */
@Slf4j
public class RunAsyncTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.put(1, "one");
        }).exceptionally(e -> {
            log.error("", e);
            return null;
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            map.put(2, "two");
            throw new RuntimeException("shit");
        }).exceptionally(e -> {
            log.error("", e);
            return null;
        });
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            map.put(3, "three");
        }).exceptionally(e -> {
            log.error("", e);
            return null;
        });

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(future1, future2, future3);
        try {
            allFuture.get();
        } catch (Exception e) {
            log.error("allGet: ", e);
        }
        log.info("{}", map);
    }
}
