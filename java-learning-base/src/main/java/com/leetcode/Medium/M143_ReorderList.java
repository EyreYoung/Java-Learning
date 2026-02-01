package com.leetcode.Medium;

import com.leetcode.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author slowdive
 * @summary 重排链表
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/12
 */

//给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//
//
//L0 → L1 → … → Ln - 1 → Ln
//
//
// 请将其重新排列后变为：
//
//
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [1,2,3,4]
//输出：[1,4,2,3]
//
// 示例 2：
//
//
//
//
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3]
//
//
//
// 提示：
//
//
// 链表的长度范围为 [1, 5 * 10⁴]
// 1 <= node.val <= 1000
//
//
// Related Topics 栈 递归 链表 双指针 👍 989 👎 0

public class M143_ReorderList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,2,3,4,5,6,7,8,9,10);
        ListNode head1 = new ListNode(1,2,3,4,5,6,7,8,9);
        reorderList2(head);
        reorderList2(head1);
        System.out.println(head);
        System.out.println(head1);
    }

    public static void reorderList(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            deque.add(cur);
            cur = cur.next;
        }
        cur = null;
        ListNode first, last;
        while (!deque.isEmpty()) {
            first = deque.pollFirst();
            if (cur != null) {
                cur.next = first;
            }
            cur = first;
            if (!deque.isEmpty()) {
                last = deque.pollLast();
                cur.next = last;
                cur = last;
            }
        }
        cur.next = null;
    }

    public static void reorderList2(ListNode head) {
        // 快慢指针找到中点（注意奇偶数）
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中点开始，反转后半部分的链表
        ListNode cur = slow;
        ListNode post = cur.next;
        cur.next = null;
        ListNode temp;
        while (post != null) {
            temp = post.next;
            post.next = cur;
            cur = post;
            post = temp;
        }
        // 偶数：
        // head = "1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null"
        // cur = "10 -> 9 -> 8 -> 7 -> 6 -> null"
        // 奇数：
        // head = "1 -> 2 -> 3 -> 4 -> 5 -> null"
        // cur  = "9 -> 8 -> 7 -> 6 -> 5 -> null"
        // 把前半部分和反转过的后半部分交叉起来
        ListNode i = head, j = cur;
        ListNode iTemp, jTemp;
        // 最后一个节点可以不管，没问题的
        while (i.next != null && j.next != null) {
            iTemp = i.next;
            jTemp = j.next;
            i.next = j;
            j.next = iTemp;
            i = iTemp;
            j = jTemp;
        }
    }

}
