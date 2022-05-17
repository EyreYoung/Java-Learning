package com.leetcode.Easy;

import com.leetcode.ListNode;

/**
 * 回文链表
 */

//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,2,1]
//输出：true
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：false
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围[1, 10⁵] 内
// 0 <= Node.val <= 9
//
//
//
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 栈 递归 链表 双指针 👍 1387 👎 0

public class E234_IsPalindrome {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                        new ListNode(-2,
                                                new ListNode(1)))));
        System.out.println(head);
        System.out.println(new E234_IsPalindrome().isPalindrome(head));
        System.out.println(new E234_IsPalindrome().isPalindrome(new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(-1))))));
    }

    public boolean isPalindrome(ListNode head) {
        // 特殊 只有一个元素
        if (head.next == null) return true;
        // 特殊 只有两个元素
        if (head.next.next == null) {
            return head.val == head.next.val;
        }

        ListNode pre = null;
        ListNode p = head, post = head.next, fast = head;
        while (fast.next != null && fast.next.next != null) {

            // 快指针两倍速度往后走
            fast = fast.next.next;

            // 慢指针用来反转前半部分的链表
            // 先全体后移
            pre = p;
            p = post;
            post = post.next;
            // 再反转指针
            p.next = pre;

        }
        head.next = null;
        // 如果fast是最后一个元素，说明链表元素个数为奇数，这是慢指针 p 走到最中间的元素，不需要判断，所有left从pre开始
        // 如果fast是倒数第二个元素，说明链表元素个数为偶数，这时慢指针 p 刚好走到第length/2个元素
        ListNode left = fast.next == null ? pre : p;
        ListNode right = post;
        // left往左，right往右，逐一比较
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

}
