package com.leetcode.Medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 207. 课程表
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class M207_CanFinish {

    public static void main(String[] args) {
        M207_CanFinish solution = new M207_CanFinish();
        System.out.println(solution.canFinish(2, new int[][]{{1, 0}})); // true
        System.out.println(solution.canFinish(2, new int[][]{{1, 0},{0, 1}})); // false
        System.out.println(solution.canFinish(1, new int[][]{})); // true
        System.out.println(solution.canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}})); // true
    }

    /**
     * BFS 拓扑排序：不断修"没有前置课"的课，最后看能不能全部修完
     * 有环的话，环上的节点入度永远不会变成 0，所以最终 count 不够
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图：为每门课创建节点
        nodes = new Node[numCourses];
        for (int i = 0; i < numCourses; i++) {
            nodes[i] = new Node(i);
        }
        // 建边：[a, b] 表示学 a 之前要先学 b → b 是 a 的前置课
        for (int[] x : prerequisites) {
            int a = x[0], b = x[1];
            nodes[a].pre.add(nodes[b]);   // a 的前置课集合加入 b
            nodes[b].next.add(nodes[a]);  // b 的后续课集合加入 a
        }
        // 找到所有入度为 0 的节点（没有前置课，可以直接修）入队
        Queue<Node> q = new ArrayDeque<>();
        for (Node node : nodes) {
            if (node != null && node.pre.isEmpty()) {
                q.add(node);
            }
        }
        // BFS：每修完一门课，把它从后续课的前置集合中移除
        while (!q.isEmpty()) {
            Node node = q.poll();
            numCourses--;  // 修完一门，剩余课数 -1
            for (Node after : node.next) {
                after.pre.remove(node);            // 后续课的前置依赖 -1
                if (after.pre.isEmpty()) q.offer(after);  // 前置课全修完了，可以修了
            }
        }
        // 如果所有课都修完了，说明无环
        return numCourses == 0;
    }

    Node[] nodes;

    static class Node {
        int a;                              // 课程编号
        Set<Node> pre = new HashSet<>();    // 前置课集合（入边）
        Set<Node> next = new HashSet<>();   // 后续课集合（出边）
        Node(int a) {
            this.a = a;
        }
    }

}
