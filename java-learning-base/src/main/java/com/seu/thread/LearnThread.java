package com.seu.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "LearnThread")
public class LearnThread {
    private static Integer count = 0;

    public static void foo() {
        synchronized (LearnThread.class) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    foo();
                    log.debug(Thread.currentThread().getName() + "'s count = " + count);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final count = " + count);
    }
}
