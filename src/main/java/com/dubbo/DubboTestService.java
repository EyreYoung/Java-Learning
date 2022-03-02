package com.dubbo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author slowdive
 * @summary RPC远程过程调用测试
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/2
 */

public interface DubboTestService {
    String hello(String name);
}
