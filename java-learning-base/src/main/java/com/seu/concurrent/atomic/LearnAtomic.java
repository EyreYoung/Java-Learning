package com.seu.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/24 7:41 下午
 */
@Slf4j(topic = "学习原子类")
public class LearnAtomic {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        ai.set(1);
        ai.getAndAdd(2);
        System.out.println(ai);
    }
}
