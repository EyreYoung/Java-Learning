package com.leetcode.Medium;

import java.util.Stack;

/**
 * @author slowdive
 * @summary 外观数列
 * @Copyright (c) 2021, Lianjia Group All Rights Reserved.
 * @since 2021/10/15
 */
public class Medium38 {
    public static void main(String[] args) {
        System.out.println(new Medium38().countAndSay(10));
    }
    public String countAndSay(int n) {
        if (n == 1) return "1";
        char[] last = countAndSay(n - 1).toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < last.length; i++) {
            if (stack.isEmpty()) {
                stack.push(last[i]);
            }
            else if (stack.peek().equals(last[i])) {
                stack.push(last[i]);
            }
            else {
                sb.append(stack.size()).append(stack.peek());
                stack.clear();
                stack.push(last[i]);
            }
            if (!stack.isEmpty() && i == (last.length - 1)) {
                sb.append(stack.size()).append(stack.peek());
            }
        }
        return sb.toString();
    }
}
