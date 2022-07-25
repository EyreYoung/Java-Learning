package com.leetcode.Medium;

import com.leetcode.ListNode;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/29
 */
//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [5], left = 1, right = 1
//输出：[5]
//
//
//
//
// 提示：
//
//
// 链表中节点数目为 n
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
//
//
//
//
// 进阶： 你可以使用一趟扫描完成反转吗？
// Related Topics 链表 👍 1255 👎 0
public class M92_ReverseBetween {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(head);
        System.out.println(reverseBetween(head, 2, 4));
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (left >= right) return head;
        int i = left;
        ListNode p = head;
        ListNode pre = null;
        // 先把p走到要反转的初始节点处
        while (i > 1) {
            pre = p;
            p = p.next;
            i--;
        }
        // 如果从最开头开始，那就免得调整pre的指向了
        if (pre == null) {
            return reverseListByN(p, right - left);
        }
        pre.next = reverseListByN(p, right - left);
        return head;
    }

    public static ListNode reverseListByN(ListNode head, int x) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode p = head;
        ListNode post = p.next;
        while (x > 0) {
            // pre -> p 反转为 pre <- p
            p.next = pre;

            // 整体后移一位
            // pre -> p -> post
            // 变为
            // 原pre <- 原p｜现pre -> 原post｜现p -> 现post
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

}
