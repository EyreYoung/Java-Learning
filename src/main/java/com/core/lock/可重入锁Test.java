package com.core.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/1/7
 */

@Slf4j(topic = "可重入锁测试")
public class 可重入锁Test {

    synchronized void setA() throws Exception {
        Thread.sleep(1000);
        setB();
    }

    synchronized void setB() throws Exception {
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws Exception {
        可重入锁Test test = new 可重入锁Test();
        test.setA();
        test.setB();
        log.debug("synchronized是可重入锁，当前线程可以再次获取其已获取的锁");
    }



}
