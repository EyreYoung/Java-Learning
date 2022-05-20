package com.leetcode.Hard;

/**
 * 二叉树中的最大路径和
 */

//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。
//
// 路径和 是路径中各节点值的总和。
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2：
//
//
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
//
//
//
//
// 提示：
//
//
// 树中节点数目范围是 [1, 3 * 10⁴]
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1585 👎 0

import com.leetcode.TreeNode;

public class H124_MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(-1), null);
        System.out.println(new H124_MaxPathSum().maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        // 不刷新的话，编译器有可能重复用之前的数据
        max = Integer.MIN_VALUE;
        find(root);
        return max;
    }

    // 以自己为路径中点的路径最大值
    public static int max;

    public static int find(TreeNode root) {
        // 特殊情况
        if(root == null) return 0;
        if (root.left == null && root.right == null) {
            max = Math.max(root.val, max);
            return root.val;
        }
        // 左右子树计算以自己为终点的路径最大值
        int leftMax = find(root.left);
        int rightMax = find(root.right);
        // 更新以自己为路径中点的路径最大值（左右最大值 + 自己， 左子树最大值 + 自己， 右子树最大值 + 自己 + 自己）
        // 因为可能有负数的情况，所以要这样算
        max = Math.max(max, leftMax + rightMax + root.val);
        max = Math.max(max, leftMax + root.val);
        max = Math.max(max, rightMax + root.val);
        max = Math.max(max, root.val);
        // 返回 （左子树最大值 + 自己，右子树最大值 + 自己，自己）中的最大值
        return Math.max(Math.max(leftMax, rightMax) + root.val, root.val);
    }

}
