package com.leetcode;

import java.util.Objects;

/**
 * @author slowdive
 * @summary 最长回文子串
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/1
 */

public class Medium5 {

    public static void main(String[] args) {
        System.out.println(new Medium5().longestPalindrome("77763475638439465734634782323424352343245234"));
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
