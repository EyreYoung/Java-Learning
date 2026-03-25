package com.leetcode.Medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 994. 腐烂的橘子
 * <p>
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * <p>
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * <p>
 * 示例 3：
 * <p>
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * <p>
 * n == grid[i].length
 * <p>
 * 1 <= m, n <= 10
 * <p>
 * grid[i][j] 仅为 0、1 或 2
 */
public class M994_OrangesRotting {

    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}})); // 4
        System.out.println(orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}})); // -1
        System.out.println(orangesRotting(new int[][]{{0,2}})); // 0
    }

    public static int orangesRotting(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int minute = 0, fresh = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        // 先统计新鲜橘子数量，并把所有初始腐烂橘子加入队列作为 BFS 起点
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) fresh++;
                else if (grid[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }
        // 没有新鲜橘子，说明一开始就已经处理完了
        if (fresh <= 0) {
            return minute;
        }
        // 按分钟一层一层扩散，当前队列里的橘子会在同一分钟同时感染周围
        while (!queue.isEmpty()) {
            if (fresh <= 0) {
                return minute;
            }
            // 收集下一分钟会变腐烂的橘子
            Queue<int[]> newQueue = new ArrayDeque<>();
            for (int[] rot : queue) {
                if(rot[0] > 0 && grid[rot[0] - 1][rot[1]] == 1) {
                    newQueue.offer(new int[]{rot[0] - 1, rot[1]});
                    grid[rot[0] - 1][rot[1]] = 2;
                    fresh--;
                }
                if(rot[1] > 0 && grid[rot[0]][rot[1] - 1] == 1) {
                    newQueue.offer(new int[]{rot[0], rot[1] - 1});
                    grid[rot[0]][rot[1] - 1] = 2;
                    fresh--;
                }
                if(rot[0] < row - 1 && grid[rot[0] + 1][rot[1]] == 1) {
                    newQueue.offer(new int[]{rot[0] + 1, rot[1]});
                    grid[rot[0] + 1][rot[1]] = 2;
                    fresh--;
                }
                if(rot[1] < col - 1 && grid[rot[0]][rot[1] + 1] == 1) {
                    newQueue.offer(new int[]{rot[0], rot[1] + 1});
                    grid[rot[0]][rot[1] + 1] = 2;
                    fresh--;
                }
            }
            minute++;
            queue = newQueue;
        }
        // 队列空了但还有新鲜橘子，说明有些位置永远感染不到
        return -1;
    }

}
