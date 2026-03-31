package com.leetcode.Medium;

/**
 * 547. 省份数量
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 * <p>
 * <p>
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class M547_FindCircleNum {

    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}})); // 2
        System.out.println(findCircleNum(new int[][]{{1,0,0},{0,1,0},{0,0,1}})); // 3
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];
        
        // 初始状态下，假设没有任何城市相连，那么省份的数量就等于城市的数量 n
        int ret = n;
        
        // 1. 并查集初始化：让每个城市最初都自成一派，自己是自己的“根节点”
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 2. 遍历邻接矩阵的右上半部分（因为是无向图，对称的）
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 如果发现城市 i 和 城市 j 是相连的
                if (isConnected[i][j] == 1) {
                    // 尝试将他们所属的省份合并。
                    // 如果合并成功（即他们原本不属于同一个省份），则总省份数量 -1
                    if (union(parent, i, j)) {
                        ret--;
                    }
                }
            }
        }
        // 最终剩下的就是互不连通的省份总数
        return ret;
    }

    // 辅助方法：合并两个城市所在的省份，返回是否发生了真实的合并
    private static boolean union(int[] parent, int i, int j) {
        // x 和 y 用来记录寻找各自“根节点”过程中向上跑了多少层（类似树的深度）
        int x = 0, y = 0;
        
        // 3. 一直向上找，直到找到城市 i 的最顶层“根节点”
        while (parent[i] != i) {
            i = parent[i];
            x++;
        }
        // 4. 一直向上找，直到找到城市 j 的最顶层“根节点”
        while (parent[j] != j) {
            j = parent[j];
            y++;
        }
        
        // 如果两个根节点是同一个，说明本来就在同一个省份里，无需由于这条边再次合并
        if (i == j) return false;
        
        // 5. 按“深度/层级”进行合并的优化策略 (Union by Rank 思维)
        // 将一个集合的根节点挂到另一个集合的根节点下
        if (x > y) {
            parent[i] = j;
        } else {
            parent[j] = i;
        }
        
        return true;
    }

}
