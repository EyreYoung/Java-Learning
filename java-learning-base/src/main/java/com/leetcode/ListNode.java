package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static Set<ListNode> set = new HashSet<>();

    public static void clearSet() {
        set.clear();
    }

    @Override
    public String toString() {
        if (set.contains(this)) return val + "(重复)";
        set.add(this);
        return val + " -> " + next;
    }
}
