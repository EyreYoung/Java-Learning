package com.spring;

import com.seu.spring.Rectangle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/18
 */

@Slf4j(topic = "Spring作用域测试")
public class ScopeTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("service.xml");
        Rectangle rectangle1 = ac.getBean(Rectangle.class);
        Rectangle rectangle2 = (Rectangle) ac.getBean("rectangle");

        log.info(String.valueOf(rectangle2.getId()));
        log.info("no.1: {}, no.2: {}", rectangle1, rectangle2);
    }
}
