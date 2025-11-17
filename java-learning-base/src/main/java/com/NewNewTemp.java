package com;

public class NewNewTemp {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
//        int[] preorder = new int[]{3,9,20,15,7};
//        int[] inorder = new int[]{9,3,15,20,7};
        int[] preorder = new int[]{1,2,4,5,6,7,3,8};
        int[] inorder = new int[]{4,2,6,5,7,1,3,8};
        TreeNode head = buildTree(preorder, inorder,
                0, preorder.length - 1,
                0, inorder.length - 1);
        System.out.println(head);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder,
                                     int preLeft, int preRight,
                                     int inLeft, int inRight) {
        TreeNode head = new TreeNode(preorder[preLeft]);

        int indexOfHead = 0;
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == head.val) {
                indexOfHead = i;
                break;
            }
        }
        int left = indexOfHead - inLeft;
        int right = inRight - indexOfHead;

        if (left <= 0) {
            head.left = null;
        } else {
            head.left = buildTree(preorder, inorder,
                    preLeft + 1, preLeft + left,
                    inLeft, indexOfHead - 1);
        }

        if (right <= 0) {
            head.right = null;
        } else {
            head.right = buildTree(preorder, inorder,
                    preRight - right + 1, preRight,
                    indexOfHead + 1, inRight);
        }

        return head;
    }

}
