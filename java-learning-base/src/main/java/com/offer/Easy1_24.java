package com.offer;

import java.util.List;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/24
 */

//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
//
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
//
//
// 限制：
//
// 0 <= 节点个数 <= 5000
public class Easy1_24 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(head);
        System.out.println(reverse(head, 2, 4));
    }

    // 进阶版：将第m——n之间的链表反转
    public static ListNode reverse(ListNode head, int m, int n) {
        if (head == null) return null;
        if (m >= n) return head;
        int i = m;
        ListNode p = head;
        ListNode pre = null;
        while (i > 1) {
            pre = p;
            p = p.next;
            i--;
        }
        if (pre == null) {
            return reverseListByN(p, n - m);
        }
        pre.next = reverseListByN(p, n - m);
        return head;
    }

    public static ListNode reverseListByN(ListNode head, int x) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode p = head;
        ListNode post = p.next;
        while (x > 0) {
            p.next = pre;

            ListNode tmp = p;
            p = post;
            post = post.next;
            pre = tmp;

            x--;

        }
        head.next = post;
        p.next = pre;
        return p;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode p = head;
        ListNode post = p.next;
        while (post != null) {
            p.next = pre;

            ListNode tmp = p;
            p = post;
            post = post.next;
            pre = tmp;

        }
        p.next = pre;
        return p;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}

        @Override
        public String toString() {
            return val + " -> " + next;
        }
    }

}
