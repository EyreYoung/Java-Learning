package com.leetcode.Medium;

import java.util.Stack;

/**
 * @author slowdive
 * @summary 验证栈序列
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

//给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时
//，返回 true；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
//
//
// 示例 2：
//
//
//输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
//
//
//
//
// 提示：
//
//
// 1 <= pushed.length <= 1000
// 0 <= pushed[i] <= 1000
// pushed 的所有元素 互不相同
// popped.length == pushed.length
// popped 是 pushed 的一个排列
//
// Related Topics 栈 数组 模拟 👍 227 👎 0

public class Medium946 {

    public static void main(String[] args) {
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,3,5,2,1};
        System.out.println(new Medium946().validateStackSequences(pushed, popped));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0; // pushed数组的index
        int j = 0; // popped数组的index
        // 【循环】pushed元素入栈 popped元素出栈
        while (i < pushed.length && j < popped.length) {
            // 先将pushed元素入栈
            stack.push(pushed[i]);
            // 【循环】检查栈顶元素与popped元素是否相同
            // 能出栈的全部出掉
            while (!stack.isEmpty() && stack.peek().equals(popped[j])) {
                // 相同元素出栈
                stack.pop();
                // popped队列向前一位
                j++;
            }
            // 继续push
            i++;
        }
        return stack.isEmpty();
    }

}
