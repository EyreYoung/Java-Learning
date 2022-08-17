package com.leetcode.Medium;

/**
 * @author slowdive
 * @summary 有序矩阵中第K小的元素
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/17
 */

//给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 请注意，它是 排序后 的第 k 小元素，而不是第
//k 个 不同 的元素。
//
// 你必须找到一个内存复杂度优于 O(n²) 的解决方案。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//输出：13
//解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
//
//
// 示例 2：
//
//
//输入：matrix = [[-5]], k = 1
//输出：-5
//
//
//
//
// 提示：
//
//
// n == matrix.length
// n == matrix[i].length
// 1 <= n <= 300
// -10⁹ <= matrix[i][j] <= 10⁹
// 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
// 1 <= k <= n²
//
//
//
//
// 进阶：
//
//
// 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
// 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。
//
//
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 👍 845 👎 0

public class M378_KthSmallest {

    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][]{{1,5,9},{10,11,13},{12,13,15}}, 8));
        System.out.println(kthSmallest(new int[][]{{-5, -4},{-5, -4}}, 2));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0], right = matrix[matrix.length - 1][matrix.length - 1];
        int mid, num;
        // 二分法
        while (left < right) {
            mid = left + (right - left) / 2;
            // 计算在矩阵中 <= mid的数量
            num = findSmaller(matrix, mid);
            if (num >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int findSmaller(int[][] matrix, int num) {
        int i = 0;
        int j = matrix.length - 1;
        int ret = 0;
        // 从右上角元素开始走
        while (i < matrix.length && j >= 0) {
            // 值比num大，就往左走一列
            if (matrix[i][j] > num) {
                j--;
            }
            // 否则就往下走一行，这时可以计算出这行 <= num的个数了
            else {
                ret += j + 1;
                i++;
            }
        }
        return ret;
    }

}
