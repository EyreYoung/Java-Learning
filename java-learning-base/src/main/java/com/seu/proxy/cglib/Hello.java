package com.seu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Hello {
    public String sayHello(String... args){
        log.info("You said: " + Arrays.toString(args));
        return "Hello, " + Arrays.toString(args) + "!";
    }
}
