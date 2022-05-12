package com.leetcode.Medium;

/**
 * @author slowdive
 * @summary 岛屿数量
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/12
 */

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1：
//
//
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] 的值为 '0' 或 '1'
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1719 👎 0

public class M200_NumIslands {

    public static void main(String[] args) {
        System.out.println(new M200_NumIslands().numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        }));
    }

    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        // path用来判断是否走过
        path = new int[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (path[i][j] > 0 || grid[i][j] == '0') continue;
                count++;
                // 从陆地出发找出自己所在的岛屿，把岛屿的每块陆地进行标记
                find(grid, i, j);
            }
        }
        return count;
    }

    int[][] path;

    public void find(char[][] grid, int i, int j) {
        // 超出矩阵边界 或者走过了 或者不是陆地 返回面积为0
        if (i >= grid.length || i < 0
                || j >= grid[0].length || j < 0
                || path[i][j] > 0
                || grid[i][j] == '0') {
            return;
        }
        // 更新路径 避免重复走
        path[i][j]++;
        find(grid, i - 1, j);
        find(grid, i + 1, j);
        find(grid, i, j - 1);
        find(grid, i, j + 1);
    }

}
