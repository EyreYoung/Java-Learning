package com.leetcode.Medium;

import com.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 662. 二叉树最大宽度
 * <p>
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * <p>
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * <p>
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * <p>
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [1,3,2,5]
 * 输出：2
 * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目范围是 [1, 3000]
 * -100 <= Node.val <= 100
 */
public class M662_WidthOfBinaryTree {

    public static void main(String[] args) {
//        System.out.println(widthOfBinaryTree(new TreeNode(1,3,2,5,3,null,9)));
        System.out.println(widthOfBinaryTree(new TreeNode(1,2,3,4,5)));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int max = 1;
        Deque<TreeNode> deque = new ArrayDeque<>();
        Deque<Integer> deque1 = new ArrayDeque<>();
        deque.push(root);
        deque1.push(1);
        while (!deque.isEmpty()) {
            Deque<TreeNode> dd = new ArrayDeque<>();
            Deque<Integer> dd1 = new ArrayDeque<>();
            while (!deque.isEmpty()) {
                TreeNode node = deque.pollFirst();
                int x = deque1.pollFirst();
                if (node.left != null) {
                    dd.addLast(node.left);
                    dd1.addLast(x * 2);
                }
                if (node.right != null) {
                    dd.addLast(node.right);
                    dd1.addLast(x * 2 + 1);
                }
            }
            deque = dd;
            deque1 = dd1;
            if (!dd1.isEmpty()) {
                max = Math.max(dd1.peekLast() - dd1.peekFirst() + 1, max);
            }
        }
        return max;
    }

}
