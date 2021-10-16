package com.seu.thread;

import java.util.concurrent.*;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/13 3:07 下午
 */
public class CallerTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Thread.currentThread().getState() = " + Thread.currentThread().getState());
        Thread.sleep(1000);
        return "hello";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        Future<String> future = Executors.newCachedThreadPool().submit(new CallerTask());
        System.out.println("future.get() = " + future.get());
        new Thread(futureTask).start();
        try {
            String result1 = futureTask.get();
            String result2 = futureTask.get();
            System.out.println(result1);
            System.out.println(result2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
