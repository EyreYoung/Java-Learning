package com.seu.lock;


import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/17 2:27 下午
 */
@Slf4j(topic = "学习Volatile")
public class LearnVolatile {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread t1 = new Thread(task, "线程T1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    log.debug("开始通知线程停止");
                    task.stop = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程T2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
    }
}

@Slf4j(topic = "Task类")
class Task implements Runnable {
    volatile boolean stop = false;
    int i = 0;

    @Override
    public void run() {
        long s = System.currentTimeMillis();
        while (!stop) {
            i++;
        }
        log.debug("线程退出" + (System.currentTimeMillis() - s));
    }
}
