package com.leetcode.Medium;

/**
 * 最长递增子序列
 */

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。
//
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：
//
//
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 数组 二分查找 动态规划 👍 2595 👎 0

public class M300_LengthOfLIS {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,4}));
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
    }
    

    public static int lengthOfLIS(int[] nums) {
        // dp[i]定义为以nums[i]为结尾的最长递增子序列长度
        // 可以不用dp，单数字就行，降低空间复杂度
        int[] dp = new int[nums.length];
        // tail[i]定义为长度为i + 1的递增子序列最小的末尾数字
        int[] tail = new int[nums.length];
        tail[0] = nums[0];
        for (int i = 1; i < tail.length; i++) {
            tail[i] = Integer.MAX_VALUE;
        }
        dp[0] = 1;
        int max_length = 1;
        // 从头到尾算一遍dp
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            // 计算dp[i]的方法：遍历[0,i)，找到能接上的最长子序列长度
            // 二分法从tail数组中找到能接上的最长子序列长度
            max = find(tail, nums[i], max_length);

            // 算出dp[i]
            dp[i] = max + 1;
            // 用更小的结尾数字更新tail数组
            tail[dp[i] - 1] = Math.min(tail[dp[i] - 1], nums[i]);
            // 顺便算个最大值
            max_length = Math.max(dp[i], max_length);
        }
        return max_length;
    }

    public static int find(int[] tail, int target, int max_length) {
        int left = 0, right = max_length, mid;
        // 注意开闭区间，左闭右开
        // right不在真正的范围内
        while (left < right) {
            mid = (left + right) / 2;
            if (tail[mid] > target) {
                right = mid;
            }
            else if (tail[mid] < target) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return left;
    }

}
