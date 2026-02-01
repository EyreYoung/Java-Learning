package com.leetcode.Hard;

import com.leetcode.ListNode;

import java.util.Arrays;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 *
 *
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 *
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */

public class H23_MergeKLists {

    public static void main(String[] args) {
        ListNode[] list = new ListNode[]{new ListNode(1,4,5), new ListNode(1,3,4),new ListNode(2,6)};
        System.out.println(Arrays.toString(list));
        System.out.println(mergeKLists(list));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode head = new ListNode();
        ListNode cur = head;
        while(true) {
            boolean allNull = true;
            int min = Integer.MAX_VALUE;
            int minI = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    allNull = false;
                    min = lists[i].val;
                    minI = i;
                }
            }
            if (allNull) {

                break;
            }
            lists[minI] = lists[minI].next;
            cur.next = new ListNode(min);
            cur = cur.next;
        }
        return head.next;
    }

    public void heapify() {

    }

}
