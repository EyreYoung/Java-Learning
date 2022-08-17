package com.leetcode.Medium;

/**
 * 全排列
 */

//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯 👍 2033 👎 0

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M46_Permute {

    public static void main(String[] args) {
        System.out.println(new M46_Permute().permute(new int[]{1,2,3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 1) return Collections.singletonList(Collections.singletonList(nums[0]));
        List<List<Integer>> ret = new ArrayList<>();

        // 元素是否已被选择过
        boolean[] used = new boolean[nums.length];
        // 存放选择
        List<Integer> temp = new ArrayList<>();
        dfs(nums, 0, ret, temp, used);
        return ret;
    }

    static void dfs(int[] nums, int depth, List<List<Integer>> ret, List<Integer> temp, boolean[] used) {
        // 排列数量够了之后，将选择放到最终结果里
        if (depth > nums.length - 1) {
            ret.add(new ArrayList<>(temp));
            return;
        }
        // 对每个还没选择过的元素遍历
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 先选择一个
                temp.add(nums[i]);
                // 标记为已使用，从而后续不会再用
                used[i] = true;
                // 递归，继续往下一层选
                dfs(nums, depth + 1, ret, temp, used);
                // 下一层选完了，对这一层的选择进行回退
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

}
