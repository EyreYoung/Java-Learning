package com.seu.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "proxyTest")
public class proxyTest {
    public static void main(String[] args) {
        // 静态代理测试
        IUserDao target = new UserDao();
        IUserDao proxy = new UserDaoProxy(target);
        target.save();
        proxy.save();

        log.debug("------测试类分隔符-----\n");

        // 动态代理测试
        JobsProxy jobsProxy = new JobsProxy();
        Person jobs = jobsProxy.getProxy();
        jobs.sing("Imagine");
    }
}
