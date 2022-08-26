package com.spring;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author slowdive
 * @summary
 * @since 2022/8/19
 */

@Component
@Scope
public abstract class SingletonTest {

    @Resource
    private PrototypeTest prototypeTest;

    @Lookup
    abstract PrototypeTest getPrototypeTest();

}
