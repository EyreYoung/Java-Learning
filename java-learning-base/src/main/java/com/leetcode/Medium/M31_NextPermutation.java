package com.leetcode.Medium;

import java.util.Arrays;

/**
 * @author slowdive
 * @summary 下一个排列
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/10
 */

//整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
//
//
// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
//
//
// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就
//是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
//
//
// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
//
//
// 给你一个整数数组 nums ，找出 nums 的下一个排列。
//
// 必须 原地 修改，只允许使用额外常数空间。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1]
//输出：[1,2,3]
//
//
// 示例 3：
//
//
//输入：nums = [1,1,5]
//输出：[1,5,1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
//
//
// Related Topics 数组 双指针 👍 1855 👎 0

public class M31_NextPermutation {

    public static void main(String[] args) {
        int[] a = new int[]{2,3,1,3,3};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }

    public static void nextPermutation(int[] nums) {
        // m表示从后往前第一个正序的数，即满足 nums[m] < nums[m+1]
        int m = -1, tmp;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                m = i - 1;
                break;
            }
        }
        // 如果找到了，找到 m 后面刚好比 nums[m]大的数
        // 即比 nums[m] 大的数中，最小的数
        // 将其与 m 位置交换
        if (m != -1) {
            int max = m + 1;
            for (int i = m + 1; i < nums.length; i++) {
                if (nums[i] > nums[m] && nums[i] <= nums[max]) {
                    max = i;
                }
            }
            tmp = nums[m];
            nums[m] = nums[max];
            nums[max] = tmp;
        }
        // 反转 m 之后的数组，因为 m 是从后往前找的，所以 m 后面一定是逆序的
        // 从而将最小的数放到最前面，即为最近的下一个排列
        // 如果一个都没找到，说明现在整个数组都是逆序的，直接全部反转
        int left = m + 1;
        int right = nums.length - 1;
        while (left < right) {
            tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

}
