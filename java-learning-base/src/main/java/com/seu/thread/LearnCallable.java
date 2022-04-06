package com.seu.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "LearnCallable")
public class LearnCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            log.debug("running futureTask...");
            Thread.sleep(1000);
            return "hello";
        });

        Thread t = new Thread(task, "t2");
        t.start();

        log.debug("get的值：{}", task.get());
    }
}
