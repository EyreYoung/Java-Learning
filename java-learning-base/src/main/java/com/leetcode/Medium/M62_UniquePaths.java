package com.leetcode.Medium;

import com.google.common.base.Stopwatch;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/28
 */
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
// 问总共有多少条不同的路径？
//
//
//
// 示例 1：
//
//
//输入：m = 3, n = 7
//输出：28
//
// 示例 2：
//
//
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
//
//
// 示例 3：
//
//
//输入：m = 7, n = 3
//输出：28
//
//
// 示例 4：
//
//
//输入：m = 3, n = 3
//输出：6
//
//
//
// 提示：
//
//
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 10⁹
//
// Related Topics 数学 动态规划 组合数学 👍 1384 👎 0
public class M62_UniquePaths {
    public static void main(String[] args) {
        int m = 51, n = 9;
        Stopwatch sw = Stopwatch.createStarted();
        System.out.println(new M62_UniquePaths().uniquePaths(m, n));
        sw.stop();
        System.out.println(sw);
        sw.reset();
        sw.start();
        System.out.println(new M62_UniquePaths().uniquePaths2(m, n));
        sw.stop();
        System.out.println(sw);
    }
    public int uniquePaths(int m, int n) {
        if (dp1 == null) {
            dp1 = new int[m][n];
            dp1[0][0] = 1;
        }
        if (dp1[m - 1][n - 1] != 0) return dp1[m - 1][n - 1];
        int ret = 0;
        if (m > 1) ret += uniquePaths(m - 1, n);
        if (n > 1) ret += uniquePaths(m, n - 1);
        dp1[m - 1][n - 1] = ret;
        return ret;
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != 0) continue;
                dp[i][j] = (i == 0 ? 0 : dp[i - 1][j]) + (j == 0 ? 0 : dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    private int[][] dp1;
}
