package com.leetcode.Medium;

import java.util.Arrays;

/**
 * 684. 冗余连接
 * <p>
 * 树可以看成是一个连通且 无环 的 无向 图。
 * <p>
 * 给定一个图，该图从一棵 n 个节点 (节点值 1～n) 的树中添加一条边后获得。添加的边的两个不同顶点编号在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * <p>
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * <p>
 * <p>
 * 提示:
 * <p>
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 *
 */
public class M684_FindRedundantConnection {

    public static void main(String[] args) {
        M684_FindRedundantConnection solution = new M684_FindRedundantConnection();
        System.out.println(Arrays.toString(solution.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
        System.out.println(Arrays.toString(solution.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}})));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        // 初始化并查集：
        // 一开始每个点各自是一个集合，所以父节点先指向自己
        for(int i = 1; i <= edges.length; i++) {
            parent[i] = i;
        }

        // 按输入顺序一条边一条边加入图中
        // 如果某条边连接的两个点本来就已经连通了，
        // 那么这条边就是导致成环的冗余边
        for(int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];

            // 这里的 union 语义是：
            // true  -> x 和 y 本来就在同一个集合里，这条边会形成环
            // false -> 合并成功，没有形成环
            if(union(x, y, parent)) {
                return new int[]{x, y};
            }
        }
        return new int[2];
    }

    public boolean union(int x, int y, int[] parent) {
        // 先找到 x 和 y 各自所在集合的根节点（也就是集合代表）
        int root1 = find(x, parent);
        int root2 = find(y, parent);

        // 如果根节点相同，说明 x 和 y 本来就已经连通了
        // 这时候再加这条边，一定会形成环
        if(root1 == root2) {
            return true;
        }

        // 如果根节点不同，说明原来不在一个集合里
        // 这时才真正执行合并
        //
        // 这里必须连根节点，不能写成 parent[x] = y
        // 因为 x 和 y 可能只是集合里的普通节点，不是整个集合的代表
        //
        // parent[root1] = root2 表示：
        // 让 root1 这个集合挂到 root2 这个集合下面
        parent[root1] = root2;
        return false;
    }

    public int find(int x, int[] parent) {
        // find 的作用：
        // 一直沿着 parent 往上找，直到找到某个点的父节点是它自己
        // 这个点就是当前集合的根节点 / 老大
        if(parent[x] != x) {
            return find(parent[x], parent);
        }
        return x;
    }

}
