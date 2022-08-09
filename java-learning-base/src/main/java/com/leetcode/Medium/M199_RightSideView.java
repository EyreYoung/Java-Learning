package com.leetcode.Medium;

import com.leetcode.TreeNode;

import java.util.*;

/**
 * @author slowdive
 * @summary 二叉树的右视图
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/8
 */

//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
//
//
// 示例 1:
//
//
//
//
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
//
//
// 示例 2:
//
//
//输入: [1,null,3]
//输出: [1,3]
//
//
// 示例 3:
//
//
//输入: []
//输出: []
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,100]
//
// -100 <= Node.val <= 100
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 738 👎 0

public class M199_RightSideView {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,null,3);
        System.out.println(rightSideView(root));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        queue1.add(root);
        // 层序遍历，把最后一个拿出来放到结果集就行
        while (!queue1.isEmpty()) {
            ret.add(queue1.peekLast().val);
            while (!queue1.isEmpty()) {
                TreeNode tmp = queue1.poll();
                if (tmp.left != null) queue2.add(tmp.left);
                if (tmp.right != null) queue2.add(tmp.right);
            }
            queue1 = new LinkedList<>(queue2);
            queue2 = new LinkedList<>();
        }
        return ret;
    }

}
