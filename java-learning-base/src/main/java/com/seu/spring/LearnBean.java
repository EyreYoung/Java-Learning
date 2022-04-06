package com.seu.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/31 10:13 上午
 */
@Lazy
@Scope("singleton")
@Component
@Slf4j(topic = "学习Bean")
public class LearnBean {
    public LearnBean() {
        log.debug("LearnBean()");
    }
}
