package com.seu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j(topic = "cglibTest")
public class cglibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Hello hello = (Hello) enhancer.create();
        log.debug(hello.sayHello("CGLib", "Proxy"));
    }
}
