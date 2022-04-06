package com.tech;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author slowdive
 * @summary 【限流】计数器
 * 控制单位时间内的请求数量。
 *
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/25
 */

@Slf4j
public class Counter {

    /**
     * {timeout}时间内的最大访问数量
     */
    private final int limit = 10;

    /**
     * 限流的时间窗口
     */
    private final long timeout = 1000;

    /**
     * 当前时间窗口的起点，每隔{timeout}刷新一次（有请求的情况下）
     */
    private long time;

    /**
     * 当前时间窗口收到的请求次数
     */
    private AtomicInteger reqCount = new AtomicInteger(0);

    /**
     * 当前时间窗口是否能够请求
     */
    public boolean limit() {
        long now = System.currentTimeMillis();
        // 如果还在上次时间窗口内
        if (now < time + timeout) {
            // 请求数++
            reqCount.addAndGet(1);
            // 判断请求数是否超出限额
            return reqCount.get() <= limit;
        }
        // 如果以及超过时间窗口
        else {
            // 刷新出新的时间窗口
            time = now;
            // 从0开始计数
            reqCount = new AtomicInteger(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Random random = new Random();
        int sleepTime;
        while (true) {
            if (counter.limit()) {
                log.info("do something");
            }
            else {
                log.error("denied!!!");
            }
            sleepTime = random.nextInt(150);
            Thread.sleep(sleepTime);
            log.warn("sleep {} ms\n", sleepTime);

        }
    }

}
