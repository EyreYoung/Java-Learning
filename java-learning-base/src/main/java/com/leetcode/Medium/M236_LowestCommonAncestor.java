package com.leetcode.Medium;

/**
 * 二叉树的最近公共祖先
 */

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
//
//
// 示例 1：
//
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
//
//
// 示例 2：
//
//
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
//
//
// 示例 3：
//
//
//输入：root = [1,2], p = 1, q = 2
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [2, 10⁵] 内。
// -10⁹ <= Node.val <= 10⁹
// 所有 Node.val 互不相同 。
// p != q
// p 和 q 均存在于给定的二叉树中。
//
// Related Topics 树 深度优先搜索 二叉树 👍 1756 👎 0

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class M236_LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3, node5, null);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1, node3, node4);
        TreeNode node2 = new TreeNode(2, null, node6);
        TreeNode root = new TreeNode(0, node1, node2);
        System.out.println(new M236_LowestCommonAncestor().lowestCommonAncestor(root, node3, node5));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ret = new ArrayList<>();
        // 递归深度遍历
        hasLeftRight(root, p, q);
        // 由于是深度遍历，最早放进结果集的一定是最近的公共祖先
        return ret.get(0);
    }

    public List<TreeNode> ret;

    /**
     * @return 返回俩数据 root下（包括root自己)是否存在p、root下（包括root自己)是否存在q
     */
    public boolean[] hasLeftRight(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new boolean[]{false, false};
        // 分别再去检查root的左右子树有没有 p 和 q
        boolean[] left = hasLeftRight(root.left, p, q);
        boolean[] right = hasLeftRight(root.right, p, q);
        // 如果root自己和左右子树既有 p 又有 q，把自己放到结果集里
        if ((root == p || left[0] || right[0])
                && (root == q || left[1] || right[1]))
            ret.add(root);
        return new boolean[]{
                // 检查：root自己是不是 p 、左子树是否有 p 、右子树是否有 p
                root == p || left[0] || right[0],
                // 检查：root自己是不是 q 、左子树是否有 q 、右子树是否有 q
                root == q || left[1] || right[1]};
    }

}
