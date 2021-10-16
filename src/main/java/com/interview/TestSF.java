package com.interview;

import java.util.Date;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/29 4:04 下午
 */
public class TestSF extends Date {
    public static void main(String[] args) throws Exception {
        new TestSF().fun();
    }

    void fun(){
        System.out.println(super.getClass().getName());
    }
}
