package com.leetcode.Easy;

import com.leetcode.ListNode;

/**
 * @author slowdive
 * @summary 链表相交
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/5/10
 */

//给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
//
// 图示两个链表在节点 c1 开始相交：
//
//
//
// 题目数据 保证 整个链式结构中不存在环。
//
// 注意，函数返回结果后，链表必须 保持其原始结构 。
//
//
//
// 示例 1：
//
//
//
//
//输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2,
//skipB = 3
//输出：Intersected at '8'
//解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
//从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
//在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
//
//
// 示例 2：
//
//
//
//
//输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
//1
//输出：Intersected at '2'
//解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
//从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
//在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
//
//
// 示例 3：
//
//
//
//
//输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
//由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
//这两个链表不相交，因此返回 null 。
//
//
//
//
// 提示：
//
//
// listA 中节点数目为 m
// listB 中节点数目为 n
// 0 <= m, n <= 3 * 10⁴
// 1 <= Node.val <= 10⁵
// 0 <= skipA <= m
// 0 <= skipB <= n
// 如果 listA 和 listB 没有交点，intersectVal 为 0
// 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
//
//
//
//
// 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
// Related Topics 哈希表 链表 双指针 👍 208 👎 0

public class INT0207_GetIntersectionNode {

    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ap = headA, bp = headB;
        int ai = 0, bi = 0;
        // 两个节点都走到末尾
        while(ap != null) {
            ap = ap.next;
            ai++;
        }
        while(bp != null) {
            bp = bp.next;
            bi++;
        }
        // 如果a链表较长，交换下位置，方便统一处理
        if (ai > bi) {
            ap = headB;
            bp = headA;
        } else {
            ap = headA;
            bp = headB;
        }
        // 计算两个链表长度之差
        int x = Math.abs(ai - bi);
        // 先让b链表把长度差走完
        while(x > 0) {
            bp = bp.next;
            x--;
        }
        // 后续应该和交点的距离就一样了
        while(ap != null && bp != null) {
            if(ap.equals(bp)) return ap;
            ap = ap.next;
            bp = bp.next;
        }
        // 走到最后都没发现交点，那就没了
        return null;
    }
}
