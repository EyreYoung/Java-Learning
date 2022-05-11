package com.leetcode.Medium;

import java.util.Arrays;

/**
 * 最接近的三数之和
 */

//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//
// 返回这三个数的和。
//
// 假定每组输入只存在恰好一个解。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
// 示例 2：
//
//
//输入：nums = [0,0,0], target = 1
//输出：0
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 1000
// -1000 <= nums[i] <= 1000
// -10⁴ <= target <= 10⁴
//
// Related Topics 数组 双指针 排序 👍 1140 👎 0

public class M16_ThreeSumClosest {

    public static void main(String[] args) {
        System.out.println(new M16_ThreeSumClosest().threeSumClosest(new int[]{0, 1, 2}, 3));
    }

    public int threeSumClosest(int[] nums, int target) {
        // 先排序
        Arrays.sort(nums);
        // 定义左右指针
        // 初始化最小差值和对应的三数之和
        int left, right, minT = nums[0] + nums[1] + nums[2], minDiff = Math.abs(minT - target);
        // i从开头遍历到倒数第三个数（最后两个位置留给左右指针）
        for (int i = 0; i < nums.length - 2; i++) {
            // 初始化左右指针的值
            left = i + 1;
            right = nums.length - 1;
            // 遍历
            while (left < right) {
                // 计算当前三个数之和与target的差值
                int diff = target - nums[i] - nums[left] - nums[right];
                // 替换最小值
                if (Math.abs(diff) < minDiff) {
                    minT = target - diff;
                    minDiff = Math.abs(diff);
                }
                // 差值为0就不用再找了，不会再接近target了
                if (diff == 0) return target;
                // target比三数之和小，则右指针往左边来一点，让和变小（因为排了序）
                if (diff < 0) right--;
                // 反过来也一样
                else left++;
            }
        }
        // 返回最接近的值
        return minT;
    }

}
