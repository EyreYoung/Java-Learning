package com.dubbo;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/2
 */

public class DubboProviderServiceImpl implements DubboProviderService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
