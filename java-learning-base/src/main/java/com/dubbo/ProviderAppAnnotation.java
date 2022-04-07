package com.dubbo;

import com.dubbo.annotation.DubboConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @author slowdive
 * @summary dubbo注解方式启动
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/7
 */
public class ProviderAppAnnotation {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DubboConfiguration.class);
        context.start();
        System.in.read();
    }
}
