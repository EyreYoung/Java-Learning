package com.leetcode.Medium;

import com.leetcode.TreeNode;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * <p>
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * <p>
 * 从根到叶子节点路径 4->0 代表数字 40
 * <p>
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 1000] 内
 * <p>
 * 0 <= Node.val <= 9
 * <p>
 * 树的深度不超过 10
 */
public class M129_SumNumbers {

    public static void main(String[] args) {
        System.out.println(sumNumbers(new TreeNode(4,9,0,5,1)));
    }

    public static int sumNumbers(TreeNode root) {
        sum(root, 0);
        return ret;
    }

    static int ret = 0;

    public static void sum(TreeNode node, int x) {
        if (node == null) return;
        int newX = x * 10 + node.val;
        if (node.left == null && node.right == null) ret += newX;
        sum(node.left, newX);
        sum(node.right, newX);
    }

}
