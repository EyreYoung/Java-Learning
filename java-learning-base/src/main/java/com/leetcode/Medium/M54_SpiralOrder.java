package com.leetcode.Medium;

import java.util.List;

/**
 * 螺旋矩阵
 */

//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
//
// 示例 2：
//
//
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 10
// -100 <= matrix[i][j] <= 100
//
// Related Topics 数组 矩阵 模拟 👍 1099 👎 0

public class M54_SpiralOrder {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(new M54_SpiralOrder().spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        int i = 0, j = 0;
//        while (up < down && left < right) {
//
//        }
        return null;
    }

}
