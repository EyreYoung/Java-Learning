package com.leetcode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/28
 */
//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
//
//
//
// 示例 1：
//
//
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。
//
// 示例 2：
//
//
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]]
//
// 示例 3：
//
//
//输入: candidates = [2], target = 1
//输出: []
//
//
//
//
// 提示：
//
//
// 1 <= candidates.length <= 30
// 1 <= candidates[i] <= 200
// candidate 中的每个元素都 互不相同
// 1 <= target <= 500
//
// Related Topics 数组 回溯 👍 1927 👎 0
public class M39_CombinationSum {
    public static void main(String[] args) {
        System.out.println(new M39_CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        return ret;
    }
    private LinkedList<Integer> seq = new LinkedList<>(); // 存放当前序列
    private List<List<Integer>> ret = new ArrayList<>(); // 存放最终结果

    /**
     * 深度遍历所有方案
     * @param candidates 原数组
     * @param target 当前序列还剩的目标和
     * @param index 仍然可选的元素序号 仍是自身的话表示可重复 自身+1表示不可重复
     */
    private void dfs(int[] candidates, int target, int index) {
        // 如果目标已经小于0 放弃这个元素
        if (target < 0) return;
        // 如果目标刚好为0 表示成功求出一个解
        if (target == 0) {
            ret.add(new ArrayList<>(seq));
            return;
        }
        // 从当前可选元素开始遍历所有候选项 将其加入序列
        for (int i = index; i < candidates.length; i++) {
            // 如果加上之后必然大于当前目标值 就算了
            if (target >= candidates[i]) {
                // 做出选择
                seq.add(candidates[i]);
                // 从当前已选的元素开始 继续深度遍历
                dfs(candidates, target - candidates[i], i);
                // 状态回退 撤销刚才选的元素
                seq.removeLast();
            }
        }
    }
}
