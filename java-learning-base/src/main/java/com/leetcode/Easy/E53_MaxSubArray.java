package com.leetcode.Easy;

/**
 * 最大子数组和
 */

//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 子数组 是数组中的一个连续部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：1
//
//
// 示例 3：
//
//
//输入：nums = [5,4,-1,7,8]
//输出：23
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
// Related Topics 数组 分治 动态规划 👍 4911 👎 0

public class E53_MaxSubArray {

    public static void main(String[] args) {
        System.out.println(new E53_MaxSubArray().maxSubArray2(new int[]{5,4,-1,7,8}));
    }

    public int maxSubArray(int[] nums) {
        // 经典的动态规划
        // dp[i]表示以i元素为结尾的子数组和最大为多少
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果以前一个元素结尾的子数组最大和小于0，则抛弃前面的，让i元素自己作为一个数组
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            // max找一下全局最大
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        // 经典的动态规划
        // dp[i]表示以i元素为结尾的子数组和最大为多少
        // 优化一下，甚至不用dp数组，一个元素就能计算
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果以前一个元素结尾的子数组最大和小于0，则抛弃前面的，让i元素自己作为一个数组
            dp = Math.max(dp, 0) + nums[i];
            // max找一下全局最大
            max = Math.max(max, dp);
        }
        return max;
    }

}
