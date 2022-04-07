package org.seu;

import com.dubbo.DubboProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author slowdive
 * @summary dubbo服务者XML方式配置
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/6
 */
public class ConsumerApp {
    public static void main(String[] args) throws IOException {
        // 加载配置
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        DubboProviderService service =
                (DubboProviderService) context.getBean("dubboProviderService");
        String s = service.sayHello("yyd");
        System.out.println(s);
        System.in.read();
    }
}
