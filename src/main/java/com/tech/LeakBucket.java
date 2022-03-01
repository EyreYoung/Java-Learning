package com.tech;

import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/25
 */

@Slf4j(topic = "漏桶")
public class LeakBucket {

    private long time;

    private Double total;

    private Double rate;

    private Double nowSize;

    public boolean limit() {
        long now = System.currentTimeMillis();
        nowSize = Math.max(0, (nowSize - (now - time) * rate));
        time = now;
        if ((nowSize + 1) < total) {
            nowSize++;
            return true;
        }
        else {
            return false;
        }
    }

}
