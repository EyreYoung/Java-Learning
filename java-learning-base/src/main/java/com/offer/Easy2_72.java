package com.offer;

import com.google.common.base.Stopwatch;

/**
 * @author slowdive
 * @summary 求平方根
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

//给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。
//
// 正数的平方根有两个，只输出其中的正数平方根。
//
// 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
//
//
//
// 示例 1:
//
//
//输入: x = 4
//输出: 2
//
//
// 示例 2:
//
//
//输入: x = 8
//输出: 2
//解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
//
//
//
//
// 提示:
//
//
// 0 <= x <= 2³¹ - 1
//
//
//
//
// 注意：本题与主站 69 题相同： https://leetcode-cn.com/problems/sqrtx/
// Related Topics 数学 二分查找 👍 10 👎 0
public class Easy2_72 {

    public static void main(String[] args) {
        Stopwatch sw = Stopwatch.createStarted();
        System.out.println(new Easy2_72().mySqrt(900000000));
        sw.stop();
        System.out.println(sw);
        sw.reset();
        sw.start();
        System.out.println(new Easy2_72().mySqrt((float) 900000000));
        System.out.println(sw);
    }

    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        long floor = 1;
        long ceil = x;
        int times = 0;
        while (ceil - floor > 1) {
            times++;
            long mid = (floor + ceil) / 2;
            long midPow2 = mid * mid;
            if (midPow2 > x) {
                ceil = (int) mid;
            } else if (midPow2 == x) {
                System.out.println(times);
                return (int) mid;
            } else {
                floor = (int) mid;
            }
        }
        return (int) floor;
    }

    // 牛顿迭代法
    public float mySqrt(float x) {
        if (x == 0) return 0;
        // 迭代值xi
        float xi = x;
        while (true) {
            if ((int) xi * (int) xi == x) {
                return (int) xi;
            }
            // 迭代函数（推导得出）
            xi = (x / xi + xi) / 2;
        }
    }

}
