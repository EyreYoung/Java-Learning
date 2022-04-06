package com.core.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/9
 */

@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> future = new CompletableFuture<>();
        CompletableFuture shit = CompletableFuture.runAsync(() -> {
            log.info("shit");
        }, new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy()));
        while (!shit.isDone()) {
            log.info("still not done!");
        }
        log.info("done!");
        shit.cancel(true);
    }
}
