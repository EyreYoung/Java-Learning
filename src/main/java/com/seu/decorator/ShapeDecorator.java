package com.seu.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "装饰器模式 抽象装饰类 ShapeDecorator")
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator (Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}
