package com.leetcode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * <p>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 8
 */

public class M22_GenerateParenthesis {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(1));
        System.out.println(generateParenthesis(8));
    }

    public static List<String> generateParenthesis(int n) {
        StringBuffer temp = new StringBuffer();
        List<String> ret = new ArrayList<>();
        generateParenthesis(n, ret, temp, 0, 0);
        return ret;
    }

    public static void generateParenthesis(int n, List<String> ret, StringBuffer temp, int left, int right) {
        if (temp.length() == n * 2) {
            ret.add(temp.toString());
            return;
        }
        if (left < n) {
            temp.append('(');
            generateParenthesis(n, ret, temp, left + 1, right);
            temp.deleteCharAt(temp.length() - 1);
        }
        if (right < left) {
            temp.append(')');
            generateParenthesis(n, ret, temp, left, right + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}
