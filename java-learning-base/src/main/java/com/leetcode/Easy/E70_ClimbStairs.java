package com.leetcode.Easy;

/**
 * 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 45
 */

public class E70_ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(4));
    }

    public static int climbStairs(int n) {
        int pre1 = 1, pre2 = 2;
        int ret = 0;
        if (n == 1) return pre1;
        if (n == 2) return pre2;
        for (int i = 3; i <= n; i++) {
            ret = pre1 + pre2;
            pre1 = pre2;
            pre2 = ret;
        }
        return ret;
    }

}
