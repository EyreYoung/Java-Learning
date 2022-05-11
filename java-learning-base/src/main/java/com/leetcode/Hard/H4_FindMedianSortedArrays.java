package com.leetcode.Hard;

/**
 * @author slowdive
 * @summary 寻找两个正序数组的中位数
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/11
 */

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。
//
//
//
// 示例 1：
//
//
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
//
// 示例 2：
//
//
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
//
//
//
//
// 提示：
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -10⁶ <= nums1[i], nums2[i] <= 10⁶
//
// Related Topics 数组 二分查找 分治 👍 5425 👎 0

public class H4_FindMedianSortedArrays {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5, 6}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // 合并奇偶数情况，如果是奇数，求两次相同的中位数；如果是偶数，求中间两个数平均值
        int left = (n1 + n2 + 1) / 2;
        int right = (n1 + n2 + 2) / 2;
        return (getKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, left)
                + getKth(nums1, 0, n1 - 1, nums2, 0, n2 - 1, right))
                * 0.5;
    }

    // 求第K小的数
    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 两个数组长度
        int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
        // 如果第一个数组比第二个长则反转，这样只需考虑数组1为空的情况
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        // 如果数组1为空，直接返回数组2的第k小的数
        if (len1 == 0) return nums2[start2 + k - 1];
        // 如果两个数组还不为空，又要求k=1的数，直接返回两个数组头元素中较小的那个
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        // 对两个数组进行切割，每个数组提供前k/2个元素
        // 然后比较最后一个数，哪个小就说明该数组不可能存在第K小的数
        // 将这些不可能存在的数去掉，递归使用剩下的元素继续计算第(k - 去掉的元素个数)小的数
        int i = start1 + Math.min(k / 2, len1) - 1;
        int j = start2 + Math.min(k / 2, len2) - 1;
        return nums1[i] < nums2[j] ?
                getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1)) :
                getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j- start2 + 1));
    }

}
