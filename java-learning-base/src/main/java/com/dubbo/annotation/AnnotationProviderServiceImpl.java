package com.dubbo.annotation;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author slowdive
 * @summary Dubbo注解方式 服务实现
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/7
 */
// Dubbo里的@Service，不是Spring里的
@Service(timeout = 1000)
public class AnnotationProviderServiceImpl implements AnnotationProviderService {
    @Override
    public String sayHi(String name) {
        return "Hi, " + name + "!";
    }
}
