package com.leetcode.Medium;

/**
 * 有序数组中的单一元素
 */

//给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
//
// 请你找出并返回只出现一次的那个数。
//
// 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
//
//
// 示例 2:
//
//
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
//
//
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 10⁵
// 0 <= nums[i] <= 10⁵
//
// Related Topics 数组 二分查找 👍 507 👎 0

public class M540_SingleNonDuplicate {

    public static void main(String[] args) {
        System.out.println(new M540_SingleNonDuplicate().singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
    }

    public int singleNonDuplicate(int[] nums) {
        // 特殊情况处理
        if (nums.length == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[nums.length - 1] != nums[nums.length - 2]) return nums[nums.length - 1];

        int left = 0, right = nums.length - 1, mid;
        // 二分查找
        while (left < right) {
            mid = (left + right) / 2;
            // 如果mid是偶数位
            if (mid % 2 == 0) {
                // 如果其与右边的数字一样 -> 单个的数字在mid右边
                if (nums[mid] == nums[mid + 1]) {
                    left = mid;
                }
                // 和左边的一样 -> 单个的数字在左边
                else if (nums[mid] == nums[mid - 1]) {
                    right = mid;
                }
                // 和左右都不一样 -> 就是mid自己
                else {
                    return nums[mid];
                }
            }
            // 如果mid是奇数位 同理
            else {
                if (nums[mid] == nums[mid - 1]) {
                    left = mid;
                } else if (nums[mid] == nums[mid + 1]) {
                    right = mid;
                } else {
                    return nums[mid];
                }
            }
        }
        return nums[left];
    }

}
