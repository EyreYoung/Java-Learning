package com.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author slowdive
 * @summary Dubbo服务提供
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/6
 */
public class ProviderApp {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring/dubbo-provider.xml");
        context.start();
        System.in.read();
    }
}
