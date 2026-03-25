package com.leetcode.Medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1926. 迷宫中离入口最近的出口
 * <p>
 * 给你一个 m x n 的迷宫矩阵 maze （下标从 0 开始），矩阵中有空格子（用 '.' 表示）和墙（用 '+' 表示）。同时给你迷宫的入口 entrance ，用 entrance = [entrancerow, entrancecol] 表示你一开始所在格子的行和列。
 * <p>
 * 每一步操作，你可以往 上，下，左 或者 右 移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离 entrance 最近 的出口。出口 的含义是 maze 边界 上的 空格子。entrance 格子 不算 出口。
 * <p>
 * 请你返回从 entrance 到最近出口的最短路径的 步数 ，如果不存在这样的路径，请你返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * 输出：1
 * 解释：总共有 3 个出口，分别位于 (1,0)，(0,2) 和 (2,3) 。
 * 一开始，你在入口格子 (1,2) 处。
 * - 你可以往左移动 2 步到达 (1,0) 。
 * - 你可以往上移动 1 步到达 (0,2) 。
 * 从入口处没法到达 (2,3) 。
 * 所以，最近的出口是 (0,2) ，距离为 1 步。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * 输出：2
 * 解释：迷宫中只有 1 个出口，在 (1,2) 处。
 * (1,0) 不算出口，因为它是入口格子。
 * 初始时，你在入口与格子 (1,0) 处。
 * - 你可以往右移动 2 步到达 (1,2) 处。
 * 所以，最近的出口为 (1,2) ，距离为 2 步。
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：maze = [[".","+"]], entrance = [0,0]
 * 输出：-1
 * 解释：这个迷宫中没有出口。
 * <p>
 * <p>
 * 提示：
 * <p>
 * maze.length == m
 * <p>
 * maze[i].length == n
 * <p>
 * 1 <= m, n <= 100
 * <p>
 * maze[i][j] 要么是 '.' ，要么是 '+' 。
 * <p>
 * entrance.length == 2
 * <p>
 * 0 <= entrancerow < m
 * <p>
 * 0 <= entrancecol < n
 * <p>
 * entrance 一定是空格子。
 */
public class M1926_NearestExit {

    public static void main(String[] args) {
        System.out.println(nearestExit(new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}}, new int[]{1, 2})); // 1
        System.out.println(nearestExit(new char[][]{{'+','+','+'},{'.','.','.'},{'+','+','+'}}, new int[]{1, 0})); // 2
        System.out.println(nearestExit(new char[][]{{'.','+'}}, new int[]{0, 0})); // -1
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        int row = maze.length, col = maze[0].length;
        int step = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(entrance);
        maze[entrance[0]][entrance[1]] = 'x';
        while (!q.isEmpty()) {
            Queue<int[]> newQ = new ArrayDeque<>();
            for (int[] location : q) {
                int x = location[0], y = location[1];
                if (maze[x][y] != 'x' && (x == 0 || x == row - 1 || y == 0 || y == col - 1)) return step;
                if (x > 0 && maze[x - 1][y] == '.') {
                    maze[x - 1][y] = '-';
                    newQ.offer(new int[]{x - 1, y});
                }
                if (x < row - 1 && maze[x + 1][y] == '.') {
                    maze[x + 1][y] = '-';
                    newQ.offer(new int[]{x + 1, y});
                }
                if (y > 0 && maze[x][y - 1] == '.') {
                    maze[x][y - 1] = '-';
                    newQ.offer(new int[]{x, y - 1});
                }
                if (y < col - 1 && maze[x][y + 1] == '.') {
                    maze[x][y + 1] = '-';
                    newQ.offer(new int[]{x, y + 1});
                }
            }
            step++;
            q = newQ;
        }
        return -1;
    }

}
