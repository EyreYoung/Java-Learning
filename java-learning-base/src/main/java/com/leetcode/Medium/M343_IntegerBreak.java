package com.leetcode.Medium;

/**
 * 整数拆分
 */

//给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
//
// 返回 你可以获得的最大乘积 。
//
//
//
// 示例 1:
//
//
//输入: n = 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。
//
// 示例 2:
//
//
//输入: n = 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
//
//
//
// 提示:
//
//
// 2 <= n <= 58
//
// Related Topics 数学 动态规划 👍 825 👎 0

public class M343_IntegerBreak {

    public static void main(String[] args) {
        System.out.println(new M343_IntegerBreak().integerBreak(10));
    }

    public int integerBreak(int n) {
        // dp[i]表示i数拆分为至少两个整数之和后的最大乘积
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        // 从3开始一直算到n
        for (int i = 3; i <= n; i++) {
            int max = 0;
            // 用j分割i，计算出乘积最大的分割方式
            for (int j = 2; j < i; j++) {
                max = Math.max(max,
                        // 分为 j 和 i-j 两部分，由于dp表示的是至少拆分了一次的最大乘积，不包括自己，所以两部分分别还要跟自己比较一下，再乘
                        Math.max(j, dp[j]) *
                                Math.max(i - j, dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

}
