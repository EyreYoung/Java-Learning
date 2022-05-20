package com.leetcode.Medium;

import com.leetcode.TreeNode;

/**
 * 二叉树展开为链表
 */

//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
//
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。
//
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 2000] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1173 👎 0

public class M114_Flatten {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), null),
                new TreeNode(5, null, new TreeNode(6)));
        new M114_Flatten().flatten(root);
        System.out.println(root);
    }

    public void flatten(TreeNode root) {
        // 遍历指针
        TreeNode cur = root;
        // 下一个要遍历的位置
        TreeNode next;
        while (cur != null) {
            // 如果cur左子树不为空
            if (cur.left != null) {
                // 找到cur左子树最右边的元素 pre
                TreeNode pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将cur的右子树转到pre的下面
                // pre不一定是cur右子树的前一个元素，比如next没有右子树的情况，不过没关系，后续遍历还是会把正确的元素移到cur右子树前面
                pre.right = cur.right;
                // cur的左子树砍掉，放到右边
                cur.right = cur.left;
                cur.left = null;
            }
            // cur的左子树处理掉了，继续处理下一个
            cur = cur.right;
        }
    }

}
