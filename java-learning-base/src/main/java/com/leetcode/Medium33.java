package com.leetcode;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/26
 */
//整数数组 nums 按升序排列，数组中的值 互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
//
//
// 示例 2：
//
//
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1
//
// 示例 3：
//
//
//输入：nums = [1], target = 0
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5000
// -10^4 <= nums[i] <= 10^4
// nums 中的每个值都 独一无二
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转
// -10^4 <= target <= 10^4
public class Medium33 {

    public static void main(String[] args) {
        System.out.println(new Medium33().search(new int[]{4,5,6,7,0,1,2}, 1));
    }

    public int search(int[] nums, int target) {
        if (target == nums[0]) return 0;
        if (target == nums[nums.length - 1]) return nums.length - 1;
        if (target > nums[0]) {
            int i = 1;
            while (i < nums.length && nums[i] > nums[i - 1]) {
                if (nums[i] == target) {
                    return i;
                }
                i++;
            }
        }
        else if (target < nums[nums.length - 1]) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] < nums[i + 1]) {
                if (nums[i] == target) {
                    return i;
                }
                i--;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (target == nums[0]) return 0;
        if (target == nums[nums.length - 1]) return nums.length - 1;
        int i = 0, j = nums.length;

        return -1;
    }
}
