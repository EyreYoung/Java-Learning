package com.tech;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/**
 * @author slowdive
 * @summary 【限流】滑动窗口
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/25
 */

@Slf4j(topic = "滑动窗口")
public class TimeWindow {

    private ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

    private int seconds;

    private int max;

    public TimeWindow(int seconds, int max) {
        this.seconds = seconds;
        this.max = max;

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep((seconds - 1) * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clean();
            }
        }).start();

    }

    public int sizeOfValid() {
        Long ms = System.currentTimeMillis() - seconds * 1000L;
        return (int) queue.stream().filter(t -> t > ms).count();
    }

    public void take() {
        long start = System.currentTimeMillis();
        try {
            int size = sizeOfValid();
            if (size > max) {
                log.error("超限");
            }
            synchronized (queue) {
                if (sizeOfValid() > max) {
                    log.error("超限");
                    log.error("queue中有 {}, 最大数量 {}", queue.size(), max);
                }
                this.queue.offer(System.currentTimeMillis());
            }
            log.info("queue中有 {}, 最大数量 {}", queue.size(), max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clean() {
        Long c = System.currentTimeMillis() - seconds * 1000L;

        Long t1 = null;
        while ((t1 = queue.peek()) != null && t1 < c ) {
            log.info("清理数据");
            queue.poll();
        }
    }

    public static void main(String[] args) {
        final TimeWindow timeWindow = new TimeWindow(10, 1);

        IntStream.range(0, 3).forEach(i -> {
            new Thread(()-> {
                while (true) {
                    try {
                        Thread.sleep(new Random().nextInt(20) * 100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    timeWindow.take();
                }
            }).start();
        });
    }

}
