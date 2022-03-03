package com.leetcode;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author slowdive
 * @summary 回文数
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/3
 */

//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
//
// 例如，121 是回文，而 123 不是。
//
//
//
//
// 示例 1：
//
//
//输入：x = 121
//输出：true
//
//
// 示例 2：
//
//
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3：
//
//
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
//
//
//
//
// 提示：
//
//
// -2³¹ <= x <= 2³¹ - 1
//
//
//
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学 👍 1841 👎 0

public class Easy9 {

    public static void main(String[] args) {
        System.out.println(new Easy9().isPalindrome(1234567899));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = new StringBuffer(String.valueOf(x)).reverse().toString();
        long l = Long.parseLong(s);
        return l == x;
    }

}
