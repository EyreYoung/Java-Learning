package com.seu.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j(topic = "JobsProxy")
public class JobsProxy {
    Jobs jobs = new Jobs();

    public Person getProxy(){
        /**
         * 参数一：代理类的类加载器
         * 参数二：被代理对象的接口
         * 参数三：InvocationHandler实现类
         */
        return (Person) Proxy.newProxyInstance(Jobs.class.getClassLoader(), Jobs.class.getInterfaces(), new InvocationHandler() {
            /**
             *
             * @param proxy 把代理对象自己传进来
             * @param method 把代理对象现在调用的方法传进来
             * @param objects 把方法参数传进来
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                // 如果要执行sing操作
                if (method.getName().equals("sing")) {
                    log.debug("代理模式:即将sing");
                    method.invoke(jobs, objects);
                    log.debug("代理模式:sing结束");
                }
                if (method.getName().equals("dance")) {
                    log.debug("代理模式:即将dance");
                    method.invoke(jobs, objects);
                }
                return null;
            }
        });
    }
}
