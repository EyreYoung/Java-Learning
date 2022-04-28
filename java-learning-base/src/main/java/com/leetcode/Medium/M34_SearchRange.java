package com.leetcode.Medium;

import java.util.Arrays;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/26
 */
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// nums 是一个非递减数组
// -10⁹ <= target <= 10⁹
public class M34_SearchRange {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new M34_SearchRange().searchRange(new int[]{5, 7, 7, 8, 8}, 8)));
    }
    public int[] searchRange(int[] nums, int target) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == target) break;
            i++;
        }
        int j = i;
        while (j < nums.length) {
            if (nums[j] != target) break;
            j++;
        }
        j--;
        if (i >= nums.length) {
            return new int[]{-1, -1};
        }
        return new int[]{i, j};
    }
}
