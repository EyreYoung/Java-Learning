package com.seu.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "装饰器模式 实体类 Circle")
public class Circle implements Shape {
    @Override
    public void draw() {
        log.debug("Shape: Circle");
    }
}
