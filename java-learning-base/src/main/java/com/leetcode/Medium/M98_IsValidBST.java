package com.leetcode.Medium;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 98. 验证二叉搜索树
 * <p>
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */

public class M98_IsValidBST {

    public static void main(String[] args) {
        System.out.println(isValidBST(new TreeNode(2,1,3)));
        System.out.println(isValidBST(new TreeNode(5,1,4,null,null,3,6)));
        System.out.println(isValidBST(new TreeNode(5,4,6,null,null,3,7)));
    }

    public static List<Integer> ret = new ArrayList<>();

    public static boolean isValidBST(TreeNode root) {
        out(root);
        for (int i = 1; i < ret.size(); i++) {
            if (ret.get(i) <= ret.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void out(TreeNode root) {
        if (root.left != null) {
            out(root.left);
        }
        ret.add(root.val);
        if (root.right != null) {
            out(root.right);
        }
    }
}
