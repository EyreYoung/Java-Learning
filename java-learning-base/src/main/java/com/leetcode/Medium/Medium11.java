package com.leetcode.Medium;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/21
 */
//给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
//
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 返回容器可以储存的最大水量。
//
// 说明：你不能倾斜容器。
//
//
//
// 示例 1：
//
//
//
//
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
// 示例 2：
//
//
//输入：height = [1,1]
//输出：1
//
//
//
//
// 提示：
//
//
// n == height.length
// 2 <= n <= 10⁵
// 0 <= height[i] <= 10⁴
//
public class Medium11 {
    public static void main(String[] args) {
        int[] a = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(new Medium11().maxArea2(a));
    }

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            int subTallest = 0;
            for (int j = height.length - 1; j > i; j--) {
                if (height[j] > height[i]) {
                    int area = (j - i) * Math.min(height[i], height[j]);
                    max = Math.max(area, max);
                    break;
                }
                else if (height[j] > subTallest){
                    int area = (j - i) * Math.min(height[i], height[j]);
                    subTallest = height[j];
                    max = Math.max(area, max);
                }
            }
        }
        return max;
    }

    // 双指针法
    public int maxArea2(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        int area;
        while(i < j) {
            area = (j - i) * Math.min(height[i], height[j]);
            max = Math.max(max, area);
            // 从高度更低的一边缩减 因为低的那边决定了
            if (height[i] > height[j]) j--;
            else i++;
        }
        return max;
    }

}
