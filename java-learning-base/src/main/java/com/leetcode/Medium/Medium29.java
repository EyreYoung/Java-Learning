package com.leetcode.Medium;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2021, Lianjia Group All Rights Reserved.
 * @since 2021/10/12
 */
public class Medium29 {
    public static void main(String[] args) {
        System.out.println(new Medium29().divide(-2147483648, -1));
    }
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        long dividend1 = dividend;
        boolean negative = (dividend1 > 0 && divisor < 0) || (dividend1 < 0 && divisor > 0);
        if (dividend1 < 0 || divisor < 0) {
            dividend1 = Math.abs(dividend1);
            divisor = Math.abs(divisor);
        }
        int ret = 0;
        while (dividend1 > 0) {
            dividend1 = dividend1 - divisor;
            if (dividend1 >= 0) {
                ret++;
            }
            if (ret == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }
        return negative ? -ret : ret;
    }
}
