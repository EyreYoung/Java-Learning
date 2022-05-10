package com.core.jvm;

/**
 * @author slowdive
 * @summary 测试字节码
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/7
 */
public class ByteCodeTest {

    private int a = 3;

    public int increment(int a) {
        return a + this.a;
    }

    public static void main(String[] args) {
        System.out.println(new ByteCodeTest().increment(4));
    }

}
