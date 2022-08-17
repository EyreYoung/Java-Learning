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
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5, new ListNode(6))))));
        reorderList(head);
        System.out.println(head);
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

    }

}
