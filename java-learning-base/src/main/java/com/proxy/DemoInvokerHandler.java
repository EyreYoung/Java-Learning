package com.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class DemoInvokerHandler implements InvocationHandler {

    private Object target; // 实际的业务实现对象

    public DemoInvokerHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("动态代理前置方法");
        Object result = method.invoke(target, args);
        log.info("动态代理后置方法");

        return result;
    }

    public Object getProxy() {
        // 创建一个代理对象
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    public static void main(String[] args) {
        Person person = new Student("Tim");
        DemoInvokerHandler invokerHandler = new DemoInvokerHandler(person);
        Person proxy = (Person) invokerHandler.getProxy();

        proxy.wakeup();
        proxy.sleep();
    }
}
