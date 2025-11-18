package com.leetcode.Easy;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 */

//给定一个二叉树，找出其最小深度。
//
//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
//说明：叶子节点是指没有子节点的节点。
//
//
//
//示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//示例 2：
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//提示：
//
//树中节点数的范围在 [0, 105] 内
//-1000 <= Node.val <= 1000

public class E111_MinDepth {

    public static void main(String[] args) {
        System.out.println(minDepth(new TreeNode(3,9,20,null,null,15,7)));
        System.out.println(minDepth(new TreeNode(2,null,3,null,4,null,5,null,6)));
    }

    public static int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            depth++;
            int size = q.size();
            TreeNode t;
            for (int i = 0; i < size; i++) {
                t = q.poll();
                if (t.left == null && t.right == null) {
                    return depth;
                }
                if (t.left != null) {
                    q.offer(t.left);
                }
                if (t.right != null) {
                    q.offer(t.right);
                }
            }
        }
        return depth;
    }

}
