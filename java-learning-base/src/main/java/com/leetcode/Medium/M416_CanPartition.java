package com.leetcode.Medium;

/**
 * 416. 分割等和子集
 * <p>
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * <p>
 * 1 <= nums[i] <= 100
 */
public class M416_CanPartition {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,5,11,5})); // true
        System.out.println(canPartition(new int[]{1,2,3,5})); // false
        System.out.println(canPartition(new int[]{1,2,5})); // false
        System.out.println(canPartition(new int[]{3,3,6,8,16,16,16,18,20})); // true
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和是奇数，不可能平分成两个相等的子集
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        // dp[j] 表示：在当前已经遍历过的数字里，是否能凑出和 j
        boolean[] dp = new boolean[target + 1];
        // 什么都不选时，可以凑出和 0
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x <= target) {
                // j 的范围是 [x, target]：
                // 小于 x 的位置不可能通过当前数字 x 更新出来；大于 target 的和也没有意义
                // 这里必须倒着遍历，避免在同一轮里重复使用当前数字 x
                // 例如正着遍历时，x=5 可能先把 dp[5] 设为 true，再立刻用这个 5 推出 dp[10]
                for (int j = target; j >= x; j--) {
                    // 如果之前能凑出 j - x，那么加上当前数字 x 后就能凑出 j
                    if (dp[j - x]) {
                        // 一旦 target 能凑出来，就可以提前返回 true
                        if (j == target) return true;
                        dp[j] = true;
                    }
                }
            }
        }
        return false;
    }

}
