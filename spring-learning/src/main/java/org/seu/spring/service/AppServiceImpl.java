package org.seu.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
@Service
@Slf4j
public class AppServiceImpl implements AppService {

    @Override
    @Cacheable(value = "hot")
    public Integer app() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            log.error("", e);
        }
        return 111;
    }

    public String str(String s) {
        return s + ", ok.";
    }

}
