package org.seu;

import org.seu.annotation.AnnotationConsumerService;
import org.seu.config.DubboConsumerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @author slowdive
 * @summary 注解方式启动Dubbo Consumer
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/7
 */
public class ConsumerAppAnnotation {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DubboConsumerConfiguration.class);
        context.start();
        AnnotationConsumerService annotationConsumerService =
                context.getBean(AnnotationConsumerService.class);
        String s = annotationConsumerService.doSayHi("yangyudong");
        System.out.println(s);
        int read = System.in.read();
    }
}
