package com.leetcode.Medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class M239_MaxSlidingWindow {

    public static void main(String[] args) {

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k - 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = k; i < nums.length - 1; i++) {
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (i - k >= deque.getFirst()) {
                deque.removeFirst();
            }
            ret[i - k] = deque.getFirst();
        }
        return ret;
    }

}
