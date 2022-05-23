package com.leetcode;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/12
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer... vars) {
        TreeNode root = newTree(0, vars);
        if (root == null) return;
        this.val = root.val;
        this.left = root.left;
        this.right = root.right;
    }

    private static TreeNode newTree(int index, Integer... vars) {
        if (index >= vars.length || vars[index] == null) return null;

        TreeNode node = new TreeNode(vars[index]);
        node.left = newTree(2 * index + 1, vars);
        node.right = newTree(2 * index + 2, vars);

        return node;
    }

    @Override
    public String toString() {
        return "{" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
