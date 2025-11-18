package com.leetcode.Medium;

import com.leetcode.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author slowdive
 * @summary 二叉树的层序遍历
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/12
 */

//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
//
//
// 示例 2：
//
//
//输入：root = [1]
//输出：[[1]]
//
//
// 示例 3：
//
//
//输入：root = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 2000] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 广度优先搜索 二叉树 👍 1321 👎 0

public class M102_LevelOrder {

    public static void main(String[] args) {
        System.out.println(levelOrder(new TreeNode(3,
                new TreeNode(9, null, null), new TreeNode(20,
                new TreeNode(15, null, null), new TreeNode(7, null, null)))));
        System.out.println(levelOrder(new TreeNode(3,9,20,null,null,15,7)));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(Collections.singletonList(root.val));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (true) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p =  queue.peek();
                if (p.left != null) queue.add(p.left);
                if (p.right != null) queue.add(p.right);
                queue.poll();
            }
            if (queue.isEmpty()) {
                break;
            }
            ret.add(queue.stream().map(i -> i.val).collect(Collectors.toList()));
        }
        return ret;
    }

}
