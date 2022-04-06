package com.core.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/10
 */

@Slf4j(topic = "Condition线程通信测试")
public class Message {

    private final Lock lock = new ReentrantLock();

    private final Condition producedMsg = lock.newCondition();

    private final Condition consumedMsg = lock.newCondition();

    private String message;

    private boolean state;

    private boolean end;

    public void consume() {

        lock.lock();

        try {
            while (!state) {
                producedMsg.await();
            }

            log.info("consume message: {}", message);
            state = false;

            consumedMsg.signal();

        } catch (InterruptedException e) {
            log.error("Thread interrupted - viewMessage");
        } finally {
            lock.unlock();
        }

    }

    public void produce(String message) {
        lock.lock();
        try {
            while (state) {
                consumedMsg.await();
            }

            log.info("produce msg: {}", message);
            this.message = message;
            state = true;
            producedMsg.signal();

        } catch (InterruptedException e) {
            log.info("Thread interrupted - publishMessage");
        } finally {
            lock.unlock();
        }
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public static void main(String[] args) {
        Message msg = new Message();
        Thread producer = new Thread(new MessageProducer(msg));
        Thread consumer = new Thread(new MessageConsumer(msg));
        producer.start();
        consumer.start();
    }

}
