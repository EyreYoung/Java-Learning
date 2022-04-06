package com.seu.concurrent.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/24 11:38 上午
 */
public class LearnAQS extends AbstractQueuedSynchronizer {
    public static void main(String[] args) throws InterruptedException {
        AbstractQueuedSynchronizer aqs = new LearnAQS();
        System.out.println(aqs.tryAcquireNanos(1, 1000));
    }

    protected LearnAQS() {
        super();
        this.setState(2);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
