package com.seu.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j(topic = "Spring Test")
public class Test {
    public static void main(String[] args) {
//        Rectangle rectangle = new Rectangle();
//        rectangle.setLength(3);
//        rectangle.setWidth(4);
//        log.debug(rectangle.toString());
        ApplicationContext ac = new ClassPathXmlApplicationContext("service.xml");
        Rectangle rectangle1 = ac.getBean("rectangle", Rectangle.class);
        Rectangle rectangle2 = ac.getBean("rectangle", Rectangle.class);
        log.debug(String.valueOf(rectangle1));
        log.debug(String.valueOf(rectangle1 == rectangle2));
    }
}
