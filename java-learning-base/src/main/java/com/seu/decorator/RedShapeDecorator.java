package com.seu.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "装饰器模式 实体装饰类 RedShapeDecorator")
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratorShape) {
        log.debug("Border Color: Red");
    }
}
