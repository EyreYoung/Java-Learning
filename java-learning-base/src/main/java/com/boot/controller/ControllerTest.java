package com.boot.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
@Controller
public class ControllerTest {

    @RequestMapping
    @Cacheable(value = "hot")
    public Integer test() {
        return 1;
    }

}
