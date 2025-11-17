package com.leetcode.Easy;

import com.leetcode.ListNode;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/29
 */
//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：[2,1]
//
//
// 示例 3：
//
//
//输入：head = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围是 [0, 5000]
// -5000 <= Node.val <= 5000
//
//
//
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
//
//
// Related Topics 递归 链表 👍 2487 👎 0
public class E206_ReverseList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(head);
        System.out.println(reverseList(head));
    }
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;

        // p从head开始
        ListNode pre = null;
        ListNode p = head;
        ListNode post = p.next;
        while (post != null) {
            // p的指针指向pre
            p.next = pre;

            // 切换 pre p post 的位置
            pre = p;
            p = post;
            post = post.next;

        }
        p.next = pre;
        return p;
    }

}
