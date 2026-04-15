package org.seu.dubbo.consumer.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.seu.dubbo.api.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DubboGreetingController {

    @DubboReference(check = false, timeout = 3000)
    private GreetingService greetingService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return greetingService.sayHello(name);
    }
}
