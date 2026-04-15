package org.seu.dubbo.provider.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.seu.dubbo.api.GreetingService;

@DubboService(timeout = 1000)
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
