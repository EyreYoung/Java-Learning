package com.leetcode.Medium;

/**
 * @author slowdive
 * @summary 岛屿的最大面积
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/12
 */

//给你一个大小为 m x n 的二进制矩阵 grid 。
//
// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都
//被 0（代表水）包围着。
//
// 岛屿的面积是岛上值为 1 的单元格的数目。
//
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,
//0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,
//0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
//
//
// 示例 2：
//
//
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 50
// grid[i][j] 为 0 或 1
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 773 👎 0

public class M695_MaxAreaOfIsland {

    public static void main(String[] args) {
        System.out.println(new M695_MaxAreaOfIsland().maxAreaOfIsland(new int[][]{
                {1,1,0,0,0},
                {1,1,1,0,0},
                {0,0,1,1,0},
                {0,0,0,1,1}}));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;
        path = new int[grid.length][grid[0].length];
        int max = 0;
        // 遍历每个点 出发找出其所在岛屿的面积
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 走过了就不用再找了
                if (path[i][j] > 0) continue;
                max = Math.max(max, find(grid, i, j));
            }
        }
        return max;
    }

    int[][] path;

    public int find(int[][] grid, int i, int j) {
        // 超出矩阵边界 或者走过了 或者不是陆地 返回面积为0
        if (i >= grid.length || i < 0
                || j >= grid[0].length || j < 0
                || path[i][j] > 0
                || grid[i][j] == 0) {
            return 0;
        }
        // 记录路径
        path[i][j]++;
        // 自身面积(1+四周DFS深度遍历)
        return 1
                + find(grid, i - 1, j)
                + find(grid, i + 1, j)
                + find(grid, i, j - 1)
                + find(grid, i, j + 1);
    }

}
