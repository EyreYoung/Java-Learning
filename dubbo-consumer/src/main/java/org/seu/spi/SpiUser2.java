package org.seu.spi;

import com.core.spi.SpiProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/27
 */
@Slf4j
public class SpiUser2 implements SpiProvider {
    @Override
    public void call() {
        log.info("{} is calling...", this.getClass().toString());
    }
}
