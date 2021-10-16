package com.seu.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "装饰器模式 测试类 DecoratorPatternTest")
public class DecoratorPatternTest {
    public static void main(String[] args) {
        Circle circle = new Circle();
        RedShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        RedShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        log.debug("circle.draw()");
        circle.draw();
        log.debug("redCircle.draw()");
        redCircle.draw();
        log.debug("redRectangle.draw()");
        redRectangle.draw();
    }
}
