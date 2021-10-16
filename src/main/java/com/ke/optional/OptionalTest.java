package com.ke.optional;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class OptionalTest {
    public static void main(String[] args) {
        User u1 = new User();
        User u2 = new User();
        u2.setName("张三");
        User u3 = null;
        Optional<User> u11 = Optional.ofNullable(u1);
        Optional<User> u12 = Optional.ofNullable(u2);
        Optional<User> u13 = Optional.ofNullable(u3);
        // 打印空user对象
        log.info("u11:" + u11.toString());
        // 打印name = 张三的user对象
        log.info("u12:" + u12.toString());
        // 打印empty
        log.info("u13:" + u13.toString());
        // 可以抛出自定义异常
        Optional.ofNullable(u3).orElseThrow(() -> new NullPointerException());
    }

    @Data
    private static class User {
        String name;

    }
}
