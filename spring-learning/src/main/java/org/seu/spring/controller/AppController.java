package org.seu.spring.controller;

import org.seu.spring.service.AppService;
import org.seu.spring.util.ResultVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
@RestController
public class AppController {

    @Resource
    private AppService appService;

    @GetMapping("/app")
    public ResultVO app() {
        return new ResultVO<>().success(appService.app());
    }

    @GetMapping("/str")
    public ResultVO str(@RequestParam String s) {
        return new ResultVO<>().success(appService.str(s));
    }

    @GetMapping("/app/create")
    public ResultVO createApp() {
        return new ResultVO<>().success();
    }

}
