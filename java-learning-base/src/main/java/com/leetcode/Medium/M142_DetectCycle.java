package com.leetcode.Medium;

//给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//
// 不允许修改 链表。
//
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
// 示例 3：
//
//
//
//
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围在范围 [0, 10⁴] 内
// -10⁵ <= Node.val <= 10⁵
// pos 的值为 -1 或者链表中的一个有效索引
//
//
//
//
// 进阶：你是否可以使用 O(1) 空间解决此题？
// Related Topics 哈希表 链表 双指针 👍 1558 👎 0

import com.leetcode.ListNode;

/**
 * 环形链表 2
 */
public class M142_DetectCycle {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(node1);
        ListNode.clearSet();
        System.out.println(detectCycle(node1));
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while (s != null && f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            // 如果快指针能追上慢指针，说明有环存在
            if (s == f) {
                // 将快指针移到开头，俩指针同步移动，一定会在环的起点遇到
                f = head;
                while (s != f) {
                    f = f.next;
                    s = s.next;
                }
                return f;
            }
        }
        return null;
    }

}
