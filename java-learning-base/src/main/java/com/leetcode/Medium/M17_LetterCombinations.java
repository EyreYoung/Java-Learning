package com.leetcode.Medium;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 哈希表 字符串 回溯 👍 1888 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author slowdive
 * @summary 电话号码的字母组合
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/10
 */
public class M17_LetterCombinations {

    public static void main(String[] args) {
        System.out.println(letterCombinations("25343345"));
    }


    static List<List<Character>> chars = Arrays.asList(
            Arrays.asList('a', 'b', 'c'),
            Arrays.asList('d', 'e', 'f'),
            Arrays.asList('g', 'h', 'i'),
            Arrays.asList('j', 'k', 'l'),
            Arrays.asList('m', 'n', 'o'),
            Arrays.asList('p', 'q', 'r', 's'),
            Arrays.asList('t', 'u', 'v'),
            Arrays.asList('w', 'x', 'y', 'z'));

    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        List<String> ret = new ArrayList<>();
        for (char c : chars.get(Character.getNumericValue(digits.charAt(0)) - 2)) {
            ret.add(String.valueOf(c));
        }
        for (int i = 1; i < digits.length(); i++) {
            ret = combine(Character.getNumericValue(digits.charAt(i)) - 2, ret);
        }
        return ret;
    }

    public static List<String> combine(int n, List<String> now) {
        List<String> ret = new ArrayList<>();
        StringBuilder sb;
        for (char c : chars.get(n)) {
            for (String s : now) {
                sb = new StringBuilder(s).append(c);
                ret.add(sb.toString());
            }
        }
        return ret;
    }

}
