package org.seu.controller;

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
public class AppController {

    @RequestMapping("/app")
    @Cacheable(value = "hot")
    public String app() {
        return "app";
    }

}
