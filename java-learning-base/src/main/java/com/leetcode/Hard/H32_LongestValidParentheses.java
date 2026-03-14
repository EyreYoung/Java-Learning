package com.leetcode.Hard;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
 * <p>
 * 左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */

public class H32_LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses("()(())"));
        System.out.println(longestValidParentheses("(()())"));
    }

    public static int longestValidParentheses(String s) {
        if (s.length() <= 1) return 0;
        int[] dp = new int[s.length()]; // dp[i]是以i为结尾的符合条件的子串最长值
        dp[0] = 0;
        dp[1] = s.charAt(0) == '(' && s.charAt(1) == ')' ? 2 : 0;
        int max = dp[1];
        for (int i = 2; i < s.length(); i++) {
            char c = s.charAt(i);
            // 以(结尾的肯定为0
            if (c == '(') {
                dp[i] = 0;
            } else {
                // i-1为(，i为)的，肯定就是dp[i - 2] + 2
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else {
                    // x的位置是上一个有可能和i匹配的位置
                    // dp[i - 1]这个区间的已经确认没问题，问题是再往前还能不能连带i这个位置扩展成更长的有效区间
                    int x = i - dp[i - 1] - 1;
                    // 确认x位置和i位置可以匹配
                    if (x >= 0 && s.charAt(x) == '(') {
                        // dp[i-1]这个区间算在内，加上x和i位置（2），再检查dp[x-1]看有没有更多可以连带的
                        dp[i] = dp[i - 1] + 2 + (x >= 1 ? dp[x - 1] : 0);
                    } else {
                        dp[i] = 0; // 匹配不上就是0
                    }
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

}
