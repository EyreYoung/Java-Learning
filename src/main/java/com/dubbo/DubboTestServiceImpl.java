package com.dubbo;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/2
 */

public class DubboTestServiceImpl implements DubboTestService {
    @Override
    public String hello(String name) {
        return "Hello, " + name;
    }
}
