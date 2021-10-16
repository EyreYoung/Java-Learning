package com.seu.spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Spring Rectangle")
public class Rectangle {
    private int width;
    private int length;

    public Rectangle(){
        log.debug("调用构造函数");
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
