package com.leetcode.Medium;

//题目描述:
//假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。
//
//其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
//
//请你返回 k 次操作后的数组。
//
//示例:
//示例:
//
//输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
//输出: [-2,0,3,5,3]
//解释:
//
//初始状态:
//[0,0,0,0,0]
//
//进行了操作 [1,3,2] 后的状态:
//[0,2,2,2,0]
//
//进行了操作 [2,4,3] 后的状态:
//[0,2,5,5,3]
//
//进行了操作 [0,2,-2] 后的状态:
//[-2,0,3,5,3]

import java.util.Arrays;

/**
 * 区间加法 差分数组方法
 */
public class M370_GetModifiedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getModifiedArray(new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}}, 5)));
    }

    public static int[] getModifiedArray(int[][] updates, int length) {
        // 初始都为0，就不用先转为差分数组了
        int[] a = new int[length];
        for (int[] update : updates) {
            // 差分数组在开头元素加上一个值，在复原时就会自动给后面所有元素加上这个值
            a[update[0]] += update[2];
            if (update[1] == length - 1) continue;
            // 如果有范围，范围最后一个元素后面的元素要把之前加上的值再减掉
            a[update[1] + 1] -= update[2];
        }
        return recover(a);
    }

    // 将普通数组转为差分数组
    private static int[] diff(int[] a) {
        int[] diff = new int[a.length];
        diff[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            diff[i] = a[i] - a[i - 1];
        }
        return diff;
    }

    // 通过差分数组复原出原数组
    private static int[] recover(int[] a) {
        int[] recover = new int[a.length];
        recover[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            recover[i] = recover[i - 1] + a[i];
        }
        return recover;
    }

}
