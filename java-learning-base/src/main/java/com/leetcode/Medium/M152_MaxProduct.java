package com.leetcode.Medium;

/**
 * 乘积最大子数组
 */

//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 测试用例的答案是一个 32-位 整数。
//
// 子数组 是数组的连续子序列。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
//
//
// 示例 2:
//
//
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 2 * 10⁴
// -10 <= nums[i] <= 10
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
//
// Related Topics 数组 动态规划 👍 1648 👎 0

public class M152_MaxProduct {

    public static void main(String[] args) {
        System.out.println(new M152_MaxProduct().maxProduct(new int[]{-2,0,1,4,0,-2,-3,2,-2}));
    }

    int[] dp, dn; // 动态规划

    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 维护俩dp数组
        // dp[i] = 以第i个元素结尾的子数组最大正数乘积
        // dn[i] = 以第i个元素结尾的子数组最小负数乘积
        dp = new int[nums.length];
        dn = new int[nums.length];
        // 初始化第一个元素
        dp[0] = Math.max(nums[0], 0);
        dn[0] = Math.min(nums[0], 0);
        // 从第二个元素开始遍历，每个元素都利用前一个元素的dp和dn计算出自己的值
        for (int i = 1; i < nums.length; i++) {
            maxProduct(nums, i);
        }
        // 找出正数dp里的最大值
        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    public void maxProduct(int[] nums, int x) {
        int n = nums[x];
        // dp[x]的值为 max(自身，自身 * 前面的最大正数，自身 * 前面的最小负数)
        dp[x] = Math.max(n, Math.max(dp[x - 1] * n, dn[x - 1] * n));
        // dn[x]的值为 min(自身，自身 * 前面的最大正数，自身 * 前面的最小负数)
        dn[x] = Math.min(n, Math.min(dp[x - 1] * n, dn[x - 1] * n));
    }

}
