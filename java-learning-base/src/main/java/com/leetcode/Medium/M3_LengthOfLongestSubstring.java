package com.leetcode.Medium;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 10⁴
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口 👍 7519 👎 0

import java.util.HashSet;
import java.util.Set;

/**
 * @author slowdive
 * @summary 无重复字符的最长子串
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/6
 */
public class M3_LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabasdfasdfasdsdfdgdfgbsdfdfgcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> x = new HashSet<>();
        int j, max = 0;
        for(int i = 0; i < s.length(); i++){
            j = i;
            while(j < s.length() && x.add(s.charAt(j))){
                j++;
            }
            x.clear();
            max = Math.max(j - i, max);
            if(i + max >= s.length()) break;
        }
        return max;
    }

}
