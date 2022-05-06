package com.leetcode.Hard;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//
//
// 注意：
//
//
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 10⁵
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1851 👎 0

import java.util.*;

/**
 * @author slowdive
 * @summary 最小覆盖子串
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/6
 */

public class H76_MinWindow {

    public static void main(String[] args) {
        System.out.println(minWindow("a", "b"));
    }

    public static String minWindow(String s, String t) {
        int l = 0, r = 0;
        Map<Character, Integer> require = new HashMap<>();
        for (char c : t.toCharArray()) {
            require.put(c, require.getOrDefault(c, 0) + 1);
        }
        int min = s.length();
        String minWindow = s;
        Map<Character, Integer> map = new HashMap<>();
        while (r < s.length()) {
            char rChar = s.charAt(r);
            r++;
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
            System.out.printf("[r++] window: [%d, %d) \n", l, r);

            char lChar = s.charAt(l);
            while (l < r &&
                    (!require.containsKey(lChar)
                            || map.getOrDefault(lChar, 0) > require.get(lChar))) {
                map.put(lChar, map.get(lChar) - 1);
                l++;
                if (l < r) {
                    lChar = s.charAt(l);
                }
                System.out.printf("[l++] window: [%d, %d) \n", l, r);
            }
            if ((r - l) < min && containAll(map, require)) {
                min = r - l;
                minWindow = s.substring(l, r);
            }
        }
        if (minWindow.equals(s) && !containAll(map, require)) {
            return "";
        }
        return minWindow;
    }

    public static boolean containAll(Map<Character, Integer> map, Map<Character, Integer> require) {
        for (Character c : require.keySet()) {
            if (map.getOrDefault(c, 0) < require.get(c)) {
                return false;
            }
        }
        return true;
    }

}
