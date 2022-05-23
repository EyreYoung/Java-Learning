package com.leetcode.Medium;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的锯齿形层序遍历
 */

//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100
//
// Related Topics 树 广度优先搜索 二叉树 👍 640 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

public class M103_ZigzagLevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,2,3,4,null,null,5);
        System.out.println(root);
        System.out.println(new M103_ZigzagLevelOrder().zigzagLevelOrder(root));
        System.out.println(new M103_ZigzagLevelOrder().zigzagLevelOrder(new TreeNode(0,1,2,3,4,null,6)));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 特殊情况
        if (root == null) return new ArrayList<>();

        // q用来层序遍历
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        // i用来判断该次遍历应该正序还是倒序
        int i = 0;
        q.add(root);
        while (!q.isEmpty()) {
            // 把q的TreeNode转成数字，同时方便改变顺序
            List<Integer> list = new ArrayList<>();
            if (i % 2 == 0) {
                for (TreeNode node : q) {
                    list.add(node.val);
                }
            } else {
                for (TreeNode node : q) {
                    list.add(0, node.val);
                }
            }
            ret.add(list);
            // 一个队列移除上层的元素，加入本层的元素
            int size = q.size();
            for (int j = 0; j < size; j++) {
                if (q.peek() == null) continue;
                if (q.peek().left != null) q.add(q.peek().left);
                if (q.peek().right != null) q.add(q.peek().right);
                q.poll();
            }
            i++;
        }
        return ret;
    }

}
