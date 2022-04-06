package com.leetcode;

//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
//
//string convert(string s, int numRows);
//
//
//
// 示例 1：
//
//
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
//
//示例 2：
//
//
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//
//
// 示例 3：
//
//
//输入：s = "A", numRows = 1
//输出："A"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 由英文字母（小写和大写）、',' 和 '.' 组成
// 1 <= numRows <= 1000
//
// Related Topics 字符串 👍 1553 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author slowdive
 * @summary Z字形变换
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/1
 */

public class Medium6 {

    public static void main(String[] args) {
        System.out.println(new Medium6().convert2("PAYPALISHIRING", 4));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[][] matrix = new char[numRows][s.length() / 2 + numRows - 1];
        int loop = 2 * (numRows - 1);
        // i: loop times
        for (int i = 0; i < s.length() / loop + 1; i++) {
            // j: index in the {i} time loop
            for (int j = 0; j < numRows && (i * loop + j) < s.length(); j++) {
                matrix[j][i * (numRows - 1)] = s.charAt(i * loop + j);
            }
            for (int j = numRows; j < loop && (i * loop + j) < s.length(); j++) {
                matrix[loop - j][j - numRows + 1 + (i * (numRows - 1))] = s.charAt(i * loop + j);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                if (aChar != '\u0000') {
                    sb.append(aChar);
                }
            }
        }
        return sb.toString();
    }

    // 减少空间消耗
    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        Map<Integer, List<Character>> matrix = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            matrix.put(i, new ArrayList<>());
        }
        int loop = 2 * (numRows - 1);
        // i: loop times
        for (int i = 0; i < s.length() / loop + 1; i++) {
            // j: index in the {i} time loop
            for (int j = 0; j < numRows && (i * loop + j) < s.length(); j++) {
                matrix.get(j).add(s.charAt(i * loop + j));
            }
            for (int j = numRows; j < loop && (i * loop + j) < s.length(); j++) {
                matrix.get(loop - j).add(s.charAt(i * loop + j));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (List<Character> characters : matrix.values()) {
            for (Character c : characters) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
