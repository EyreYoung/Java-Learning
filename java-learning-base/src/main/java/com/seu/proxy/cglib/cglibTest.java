package com.seu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class cglibTest {
    public static void main(String[] args) {

        log.info("CGLib 与 JDK 动态代理之间可以相互补充：在目标类实现接口时，使用 JDK 动态代理创建代理对象；当目标类没有实现接口时，使用 CGLib 实现动态代理的功能");

        Hello hello = (Hello) new CglibProxy().getProxy(Hello.class);
        log.info("返回值: " + hello.sayHello("CGLib", "Proxy", "Others"));
    }
}
