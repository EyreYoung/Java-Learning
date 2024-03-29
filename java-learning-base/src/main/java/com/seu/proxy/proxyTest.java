package com.seu.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "proxyTest")
public class proxyTest {
    public static void main(String[] args) {

        log.info("FYI: https://learn.lianglianglee.com/专栏/Dubbo源码解读与实战-完/08%20%20代理模式与常见实现.md");

        // 静态代理测试
        IUserDao target = new UserDao();
        IUserDao proxy = new UserDaoProxy(target);

        log.debug("普通调用:");
        target.save();

        log.debug("静态代理调用:");
        proxy.save();

        log.debug("动态代理调用:");

        // 动态代理测试
        JobsProxy jobsProxy = new JobsProxy();
        Person jobs = jobsProxy.getProxy();
        jobs.dance("Imagine");
    }
}
