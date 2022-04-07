package com.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author slowdive
 * @summary dubbo服务者API方式配置
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/6
 */
public class ProviderAppApi {
    public static void main(String[] args) throws IOException {

        // 实现要提供的服务
        DubboProviderService service = new DubboProviderServiceImpl();

        // dubbo配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("api-provider");
        applicationConfig.setOwner("yyd");

        // dubbo注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://localhost:2181");
        registryConfig.setCheck(false);
        /*
         可以加上用户名密码
         registryConfig.setUsername("");
         registryConfig.setPassword("");
        */

        // dubbo协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);

        // dubbo provider暴露服务配置
        ServiceConfig<DubboProviderService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(DubboProviderService.class);
        serviceConfig.setRef(service);

        // dubbo服务暴露
        serviceConfig.export();

        System.in.read();

    }
}
