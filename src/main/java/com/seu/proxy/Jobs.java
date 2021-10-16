package com.seu.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Jobs")
public class Jobs implements Person {
    @Override
    public void sing(String line) {
        log.debug("Jobs sings " + line);
    }

    @Override
    public void dance(String name) {
        log.debug("Jobs dances " + name);
    }
}
