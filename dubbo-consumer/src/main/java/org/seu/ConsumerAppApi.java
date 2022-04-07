package org.seu;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.dubbo.DubboProviderService;

import java.io.IOException;

/**
 * @author slowdive
 * @summary dubbo服务者API方式配置
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/6
 */
public class ConsumerAppApi {
    public static void main(String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-consumer");
        application.setOwner("yyd");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://localhost:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        ReferenceConfig<DubboProviderService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DubboProviderService.class);

        // 和本地bean一样使用xxxService
        DubboProviderService service = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        String s = service.sayHello("yyd");
        System.out.println(s);

        System.in.read();

    }
}
