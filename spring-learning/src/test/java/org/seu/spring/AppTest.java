package org.seu.spring;

import org.junit.jupiter.api.Test;
import org.seu.spring.controller.AppController;

import javax.annotation.Resource;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
public class AppTest {

    @Resource
    private AppController appController;

    @Test
    public void appTest() {
        Integer app = (Integer) appController.app().getData();
        System.out.println(app);
    }

}
