package com.interview;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2021, Lianjia Group All Rights Reserved.
 * @since 2021/8/27
 */
public class main {
    public static void main(String[] args) {
        int[] a = {4,1,2,1,2};
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            r = a[i] ^ r;
            System.out.println(r);
        }
    }
}
