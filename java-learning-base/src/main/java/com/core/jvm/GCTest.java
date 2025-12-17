package com.core.jvm;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;

import java.lang.ref.WeakReference;

@Slf4j
public class GCTest {

    public static void main(String[] args) {
        ThreadLocal<Object> d = new ThreadLocal<>();
        WeakReference<Object> weakRef = new WeakReference<>(new Object());
        d.set(new Object());
        log.info("GC前: {}", weakRef.get());

        System.gc();

        log.info("GC后: {}", weakRef.get());
        log.info("GC后: {}", d.get());

    }

}
