package com.leetcode.Medium;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1], n = 1
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1,2], n = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中结点的数目为 sz
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
//
//
//
// 进阶：你能尝试使用一趟扫描实现吗？
// Related Topics 链表 双指针 👍 2007 👎 0

/**
 * 删除链表的倒数第N个结点
 */
public class M19_RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(head);
        System.out.println(removeNthFromEnd(head, 5));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode pre = null; // 用来保存要删掉的目标节点的前一个
        ListNode p = head; // 预计删掉的节点
        ListNode post = head; // 用来卡住倒数n个节点
        // 先把倒数几个走掉
        while (n > 0) {
            post = post.next;
            n--;
        }
        // 如果把倒数的走完，就已经到了末尾，说明要删掉的是头节点
        if (post == null) {
            return p.next;
        }
        // 如果post还没走到末尾，就依次往后走
        while (post != null) {
            pre = p;
            post = post.next;
            p = p.next;
        }
        // 删掉p节点
        pre.next = p.next;
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        @Override
        public String toString() {
            return val + " -> " + next;
        }
    }
}
