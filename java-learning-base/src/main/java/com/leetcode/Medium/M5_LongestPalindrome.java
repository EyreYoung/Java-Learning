package com.leetcode.Medium;

import com.google.common.base.Stopwatch;

import java.util.Objects;

/**
 * @author slowdive
 * @summary 最长回文子串
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/1
 */
//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母组成
//
// Related Topics 字符串 动态规划 👍 5127 👎 0
public class M5_LongestPalindrome {

    public static void main(String[] args) {
        Stopwatch sw = Stopwatch.createStarted();
        System.out.println(new M5_LongestPalindrome().longestPalindrome("777634756384394657346347823234243523432452344564356456345746723485734853274627238172831203874182374128371283741283471208374102983741283123412351231312351235121234554321575346245654674563675467567435745734573"));
        sw.stop();
        System.out.println(sw);
        sw.reset();
        sw.start();
        System.out.println(new M5_LongestPalindrome().longestPalindrome2("777634756384394657346347823234243523432452344564356456345746723485734853274627238172831203874182374128371283741283471208374102983741283123412351231312351235121234554321575346245654674563675467567435745734573"));
        sw.stop();
        System.out.println(sw);
    }

    // 中心扩散寻找字符串以第l和第r元素为中心的最长回文子串
    // l = r 说明是奇数个数，中心只有一个元素
    public String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) != s.charAt(r)) {
                break;
            }
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public String longestPalindrome2(String s) {
        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            if (s1.length() > ret.length()) {
                ret = s1;
            }
            if (s2.length() > ret.length()) {
                ret = s2;
            }
        }
        return ret;
    }

    // 暴力
    public String longestPalindrome(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return "";
        }
        int maxLen = 1;
        int maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 1;
            char iChar = s.charAt(i);
            int left= i - 1;
            int right = i + 1;
            // 向左找边界 aaaaa这种字符串
            while (left >= 0 && s.charAt(left) == iChar) {
                left--;
                len++;
            }
            // 向右找边界
            while (right <= s.length() - 1 && s.charAt(right) == iChar) {
                right++;
                len++;
            }
            // 两边一起找
            while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                len += 2;
            }
            if (len > maxLen) {
                maxLeft = left + 1;
                maxLen = len;
            }
        }
        return s.substring(maxLeft, maxLeft + maxLen);
    }

}
