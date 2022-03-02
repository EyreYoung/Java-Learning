package com.leetcode;

/**
 * @author slowdive
 * @summary 整数反转
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/2
 */

//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -2³¹ <= x <= 2³¹ - 1
//
// Related Topics 数学 👍 3410 👎 0

public class Medium7 {

    public static void main(String[] args) {
        System.out.println(new Medium7().reverse(-2147483648));
    }

    public int reverse(int x) {
        boolean flag = true;
        long xx = x;
        if (x < 0) {
            xx = -xx;
            flag = false;
        }
        String backwards = new StringBuffer(String.valueOf(xx)).reverse().toString();
        long s = Long.parseLong(backwards);
        if (s > Integer.MAX_VALUE || s < Integer.MIN_VALUE) {
            return 0;
        }
        return flag ? Integer.parseInt(backwards) : -Integer.parseInt(backwards);
    }

}
