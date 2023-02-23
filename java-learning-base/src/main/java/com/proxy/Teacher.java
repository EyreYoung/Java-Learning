package com.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Teacher implements Person{

    @Override
    public void wakeup() {
        log.info("Teacher醒了 准备改作业");
    }

    @Override
    public void sleep() {
        log.info("Teacher累了 准备睡觉");
    }
}
