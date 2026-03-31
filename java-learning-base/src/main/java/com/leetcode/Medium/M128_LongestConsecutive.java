package com.leetcode.Medium;

import java.util.HashSet;

/**
 * 128. 最长连续序列
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 示例 3：
 * <p>
 * 输入：nums = [1,0,1,2]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class M128_LongestConsecutive {

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2})); // 4
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1})); // 9
        System.out.println(longestConsecutive(new int[]{1,0,1,2})); // 3
    }

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // 用 HashSet 去重，并把查找某个数字是否存在的时间降到 O(1)
        for (int i : nums) {
            set.add(i);
        }
        int max = 0;
        for (int num : set) {
            int x = num, count = 1;
            // 只有当前数字没有前驱时，才把它当作连续序列的起点
            if (set.contains(x - 1)) {
                continue;
            }
            // 从起点一路向右扩展，统计这段连续序列的长度
            while (set.contains(++x)) {
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }

}
