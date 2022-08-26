package com.leetcode.Medium;

import com.leetcode.ListNode;

/**
 * @author slowdive
 * @summary 排序链表
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/16
 */

//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
//
// 示例 2：
//
//
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内
// -10⁵ <= Node.val <= 10⁵
//
//
//
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1745 👎 0

public class M148_SortList {

    public static void main(String[] args) {
        System.out.println(sortList(new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))))));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode pre = head;
        ListNode p = head.next;
        ListNode post;
        while (p != null) {
            post = p.next;
            // 发现逆序的时候，找到合适的位置把节点移过去
            if (p.val < pre.val) {
                ListNode cur = head;
                ListNode curPre = null;
                // 找刚好大于p值的前一个节点
                while (cur != null) {
                    // 小于p的跳过
                    if (cur.val <= p.val) {
                        curPre = cur;
                        cur = cur.next;
                    } else {
                        // 移动
                        pre.next = p.next;
                        if (curPre == null) {
                            head = p;
                        } else {
                            curPre.next = p;
                        }
                        p.next = cur;
                        p = post;
                        break;
                    }
                }
            } else {
                pre = p;
                p = post;
            }
        }
        return head;
    }

}
