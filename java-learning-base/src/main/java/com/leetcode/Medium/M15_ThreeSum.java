package com.leetcode.Medium;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：[]
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 3000
// -10⁵ <= nums[i] <= 10⁵
//
// Related Topics 数组 双指针 排序 👍 4739 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author slowdive
 * @summary 三数之和
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/6
 */
public class M15_ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{0,0,0}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        // 先排序
        int[] sortedNums = Arrays.stream(nums).sorted().toArray();
        int left, right, sum;
        // 以i为起点，相当于找i之后数组的两数之和
        for (int i = 0; i < nums.length; i++) {
            // 如果第i个数大于0，后面的数一定比0大，所以不用考虑
            if (sortedNums[i] > 0) {
                break;
            }
            // 如果遍历到的i上的数与前一个一样，就不用再考虑，否则会有重复三元组
            // 起点值一样的情况下，只考虑最前面的起点，因为后面的重复项可能作为left或者right用
            if (i > 0 && sortedNums[i] == sortedNums[i - 1]) {
                continue;
            }
            // 左右指针
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = sortedNums[i] + sortedNums[left] + sortedNums[right];
                // 和大于0，所以right取值要小点
                if (sum > 0) {
                    right--;
                }
                // 和大于0，所以left取值要大点
                else if (sum < 0){
                    left++;
                }
                // 和为0，要检查重复项，然后再写入结果集
                else {
                    // 右指针只考虑最右边的那个元素，因为左边的重复项可能用作left
                    if (right < nums.length - 1 && sortedNums[right + 1] == sortedNums[right]) {
                        right--;
                        continue;
                    }
                    // 左指针只考虑最左边那个元素，因为右边的重复项可能用作right
                    if (left > i + 1 && sortedNums[left - 1] == sortedNums[left]) {
                        left++;
                        continue;
                    }
                    // 没有重复项，则左右一起缩窄
                    ret.add(Arrays.asList(sortedNums[i], sortedNums[left], sortedNums[right]));
                    right--;
                    left++;
                }
            }
        }
        return ret;
    }

}
