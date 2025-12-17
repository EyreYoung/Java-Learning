package com.core.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CountDownLatchTest {

    private static ExecutorService service = new ThreadPoolExecutor(5, 5, 10, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException {

        batchProcess();
//        service.shutdown();

    }

    public static void batchProcess() throws InterruptedException {
        int threadCount = 5;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            service.execute(() -> {
                if (finalI == 3) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("{}号子任务完成", finalI);
                latch.countDown();
            });

        }

        boolean success = latch.await(10, TimeUnit.SECONDS);
        if (success) {
            log.info("Success");
        } else {
            log.info("Not Success");
        }

    }

}
