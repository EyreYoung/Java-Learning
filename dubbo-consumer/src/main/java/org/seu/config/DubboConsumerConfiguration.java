package org.seu.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author slowdive
 * @summary Dubbo consumer配置类
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/7
 */
@Configuration
@EnableDubbo(scanBasePackages = "org.seu.annotation")
@ComponentScan(value = {"org.seu.annotation"})
public class DubboConsumerConfiguration {

    // 应用配置
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setOwner("yyd");
        config.setName("annotation-consumer");
        Map<String, String> map = new HashMap<>();
        map.put("qos.enable", "true");
        map.put("qos.accept.foreign.ip", "false");
        map.put("qos.port", "33333");
        config.setParameters(map);
        return config;
    }

    // 消费者配置
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig config = new ConsumerConfig();
        config.setTimeout(3000);
        return config;
    }

    // 注册中心配置
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig config = new RegistryConfig();
        config.setProtocol("zookeeper");
        config.setAddress("localhost");
        config.setPort(2181);
        return config;
    }

}
