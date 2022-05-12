package com.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/11
 */
@Slf4j
public class Student implements Person {

    private String name;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void wakeup() {
        log.info("学生[{}]醒来了", name);
    }

    @Override
    public void sleep() {
        log.info("学生[{}]睡觉了", name);
    }
}
