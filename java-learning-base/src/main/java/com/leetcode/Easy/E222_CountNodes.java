package com.leetcode.Easy;

import com.leetcode.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * <p>
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（从第 0 层开始），则该层包含 1~ 2h 个节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目范围是[0, 5 * 104]
 * 0 <= Node.val <= 5 * 104
 * 题目数据保证输入的树是 完全二叉树
 * <p>
 * <p>
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 */

public class E222_CountNodes {

    public static void main(String[] args) {
        // 注意，Node的val不一定是层序遍历的序号，val值没用
        System.out.println(countNodes(new TreeNode(1,2,3,4,5,6)));
        System.out.println(countNodes(new TreeNode(1,2,3,4,5,6,7,8,9)));
        System.out.println(countNodes(new TreeNode(1,2,3,4,5,6,7,8,9,10,11,12,1)));
    }

    public static int countNodes(TreeNode root) {
        return countNodes(root, 1);
    }

    public static int countNodes(TreeNode root, int val) { // val代表这个Node层序遍历的真正序号
        if (root == null) {
            return 0;
        }
        // 左子树为空，那这个节点就是最终答案（因为只会一路找到最终节点的父节点）
        if (root.left == null) {
            return val;
        }
        // 如果左边不为空，右边为空，那右子节点就是最终答案
        if (root.right == null) {
            return val * 2;
        }
        // 如果左子树的树高跟右子树树高一样，答案就在右子树上
        if (countLeftLevel(root.left) == countLeftLevel(root.right)) {
            return countNodes(root.right, val * 2 + 1);
        }
        // 同理
        else {
            return countNodes(root.left,val * 2 );
        }
    }

    public static int countLeftLevel(TreeNode root) {
        TreeNode p = root;
        int l = 0;
        while (p != null) {
            l++;
            p = p.left;
        }
        return l;
    }



}
