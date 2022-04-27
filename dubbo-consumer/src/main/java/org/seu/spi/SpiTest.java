package org.seu.spi;

import com.core.spi.SpiProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.ServiceLoader;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/27
 */
@Slf4j
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<SpiProvider> serviceLoader = ServiceLoader.load(SpiProvider.class);
        serviceLoader.forEach(SpiProvider::call);
    }
}
