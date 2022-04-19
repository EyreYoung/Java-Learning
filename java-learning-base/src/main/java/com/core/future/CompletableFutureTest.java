package com.core.future;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/9
 */

@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) {
//        CompletableFuture<String> future = new CompletableFuture<>();
//        CompletableFuture shit = CompletableFuture.runAsync(() -> {
//            log.info("shit");
//        }, new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy()));
//        while (!shit.isDone()) {
//            log.info("still not done!");
//        }
//        log.info("done!");
//        shit.cancel(true);

        Map<String, Integer> m = new HashMap<>();
//        m.put("one", 1);
        System.out.println(m.get("two"));

        CompletableFuture<String> future1 = CompletableFuture
                .supplyAsync(() -> supply1("s"));
        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> supply2("ss"))
                .exceptionally(e -> {
                    log.error("", e);
                    return "error2";
                });
        CompletableFuture<String> future = CompletableFuture
                .allOf(future1, future2)
                .thenApply(v -> {
                    String s1 = "";
                    String s2 = "";
                    try {
                        s1 = future1.get();
                        s2 = future2.get();
                    } catch (Exception e) {
                        log.error("what the hell");
                    }
                    return s1 + " | " + s2;
                });
        String end = "";
        try {
            end = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("what the fuck");
            log.error("", e);
        }
        log.info("end: {}", end);
    }

    public static String supply1(String s) {
        if (Objects.equals(s, "s")) {
            throw new RuntimeException();
        }
        return s + "!!!";
    }

    public static String supply2(String s) {
        if (Objects.equals(s, "s")) {
            throw new RuntimeException();
        }
        return s + "!!!";
    }

}
