package com.seu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j(topic = "MyMethodInterceptor")
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.debug("You said: " + Arrays.toString(objects));
//        return methodProxy.invokeSuper(o, objects);
        return "I'm not gonna do that, Bitch!";
    }
}
