package com.leetcode.Medium;

import com.leetcode.ListNode;

/**
 * @author slowdive
 * @summary 两两交换链表中的节点
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/11
 */

//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
// Related Topics 递归 链表 👍 1381 👎 0

public class M24_SwapPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5,
                                                new ListNode(6))))));
        System.out.println(new M24_SwapPairs().swapPairs(head));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        int i = 0;
        ListNode post = head;
        ListNode p = null;
        ListNode pre;
        while (post.next != null) {
            // 向后移动
            pre = p;
            p = post;
            post = post.next;
            i++;

            // 保证隔一个 交换一次
            if (i % 2 != 0) {
                // 切换链接，交换p和post之间的指向
                // 如果是第一轮，无需切换pre指向post
                if (pre != null) pre.next = post;
                p.next = post.next;
                post.next = p;

                // 交换p和post的身份
                ListNode tmp = p;
                p = post;
                post = tmp;

                // 第一次结束要记得把head换一下，因为head也参与了交换
                if (i == 1) head = p;
            }
        }
        return head;
    }

}
