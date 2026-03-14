package com.leetcode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

public class M560_SubarraySum {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1,2,3}, 3));
        System.out.println(subarraySum(new int[]{1,1,1}, 2));
        System.out.println(subarraySum(new int[]{1}, 0));
    }

    public static int subarraySum(int[] nums, int k) {
        // key: 前缀和，value: 该前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 前缀和为 0 先出现 1 次，表示从下标 0 开始的子数组也可以被统计到
        map.put(0, 1);
        int ret = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 如果之前出现过前缀和 sum - k，说明中间这段子数组之和为 k
            ret += map.getOrDefault(sum - k, 0);
            // 记录当前前缀和，供后面的元素继续使用
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ret;
    }

}
