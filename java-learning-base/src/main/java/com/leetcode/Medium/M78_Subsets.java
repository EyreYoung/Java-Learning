package com.leetcode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */

public class M78_Subsets {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
        System.out.println(subsets(new int[]{0}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        sub(nums, 0, temp, ret);
        return ret;
    }

    public static void sub(int[] nums, int depth, List<Integer> temp, List<List<Integer>> ret) {
        ret.add(new ArrayList<>(temp));
        // 为了简化，每层从自己的depth开始计数（这题凑巧可以），不用额外定义一个变量
        for (int i = depth; i < nums.length; i++) {
            temp.add(nums[i]);
            sub(nums, ++depth, temp, ret);
            temp.remove(temp.size() - 1);
        }
    }
}
