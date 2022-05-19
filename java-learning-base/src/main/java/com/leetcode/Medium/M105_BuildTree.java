package com.leetcode.Medium;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 从前序与中序遍历序列构造二叉树
 */

//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。
//
//
//
// 示例 1:
//
//
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
//
//
//
//
// 提示:
//
//
// 1 <= preorder.length <= 3000
// inorder.length == preorder.length
// -3000 <= preorder[i], inorder[i] <= 3000
// preorder 和 inorder 均 无重复 元素
// inorder 均出现在 preorder
// preorder 保证 为二叉树的前序遍历序列
// inorder 保证 为二叉树的中序遍历序列
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1597 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

public class M105_BuildTree {

    public static void main(String[] args) {
//        System.out.println(new M105_BuildTree().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));

        System.out.println(new M105_BuildTree().buildTree(new int[]{1,2}, new int[]{2,1}));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return buildTree(
//                Arrays.stream(preorder).boxed().collect(Collectors.toList()),
//                Arrays.stream(inorder).boxed().collect(Collectors.toList()));
        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(List<Integer> pre, List<Integer> in) {
        if (pre.size() == 0) return null;
        if (pre.size() == 1) return new TreeNode(pre.get(0));
        int root = pre.get(0);
        List<Integer> leftIn = in.subList(0, in.indexOf(root));
        List<Integer> rightIn = in.subList(in.indexOf(root) + 1, in.size());
        List<Integer> leftPre = new ArrayList<>();
        List<Integer> rightPre = new ArrayList<>();
        for (int i = 1; i < pre.size(); i++) {
            if (i <= leftIn.size()) {
                leftPre.add(pre.get(i));
            } else {
                rightPre.add(pre.get(i));
            }
        }
        return new TreeNode(root, buildTree(leftPre, leftIn), buildTree(rightPre, rightIn));
    }

    /**
     * 用来递归
     * @param preorder 跟题干的一样，不用动
     * @param preStart 前序开始位置
     * @param preEnd 前序结束位置
     * @param inorder 跟题干的一样，不用动
     * @param inStart 中序开始位置
     * @param inEnd 中序结束位置
     * @return 构造出的该子树
     */
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                              int[] inorder, int inStart, int inEnd) {
        // 如果只有一个元素，直接返回自己作为叶子节点
        if (preEnd - preStart == 0) return new TreeNode(preorder[preStart]);
        int root = preorder[preStart];
        // 查找root在中序列表中的下标
        int rootIn = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root) {
                rootIn = i;
                break;
            }
        }
        // 计算root节点左右子树的个数
        int leftNum = rootIn - inStart;
        int rightNum = inEnd - rootIn;
        // 如果哪边个数为0，那边的子树就是null
        TreeNode leftTree = leftNum == 0 ? null : buildTree(preorder, preStart + 1, preStart + leftNum,
                inorder, inStart, rootIn - 1);
        TreeNode rightTree = rightNum == 0 ? null : buildTree(preorder, preEnd - rightNum + 1, preEnd,
                inorder, rootIn + 1, inEnd);
        return new TreeNode(root, leftTree, rightTree);
    }



}
