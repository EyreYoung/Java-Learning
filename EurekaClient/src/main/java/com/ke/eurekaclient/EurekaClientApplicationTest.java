package com.ke.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplicationTest.class, args);
    }

}
