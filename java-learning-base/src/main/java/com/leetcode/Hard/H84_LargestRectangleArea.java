package com.leetcode.Hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


/**
 * 84. 柱状图中最大的矩形
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class H84_LargestRectangleArea {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1})); // 2
        System.out.println(largestRectangleArea(new int[]{1, 2, 1})); // 3
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3})); // 10
    }

    // 做法一：两次遍历，分别求每根柱子向右、向左能延伸多少格（含自身）
    // 最后面积 = 高度 * (next[i] + pre[i] - 1)，减 1 是因为自身被算了两次
    public static int largestRectangleArea(int[] heights) {
        int max = 0;
        int[] pre = new int[heights.length];   // 向左能延伸的格数（含自身）
        int[] next = new int[heights.length];  // 向右能延伸的格数（含自身）
        Stack<Integer> stack = new Stack<>();

        // 从左往右扫，单调递增栈，求每根柱子向右第一个比它矮的位置
        for (int i = 0; i < heights.length; i++) {
            int x = heights[i];
            while (!stack.isEmpty() && x < heights[stack.peek()]) {
                int pop = stack.pop();
                next[pop] = i - pop; // 向右延伸到 i 之前（i 比它矮，挡住了）
            }
            stack.push(i);
        }
        // 栈里剩的都是右边没有更矮的，可以一直延伸到末尾
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            next[pop] = heights.length - pop;
        }

        // 从右往左扫，单调递增栈，求每根柱子向左第一个比它矮的位置
        for (int i = heights.length - 1; i >= 0; i--) {
            int x = heights[i];
            while (!stack.isEmpty() && x < heights[stack.peek()]) {
                int pop = stack.pop();
                pre[pop] = pop - i; // 向左延伸到 i 之前（i 比它矮，挡住了）
            }
            stack.push(i);
        }
        // 栈里剩的都是左边没有更矮的，可以一直延伸到开头
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            pre[pop] = pop + 1;
        }

        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (next[i] + pre[i] - 1));
        }
        return max;
    }

    // 做法二：一次遍历，弹栈时左右边界都已知
    // 右边界 = 当前 i（把它挤出去的那个柱子）
    // 左边界 = 弹出后的新栈顶（它左边第一个比它矮的柱子）
    // 宽度 = 栈空说明左边没有更矮的，宽度就是 i；否则是 i - 栈顶 - 1
    public static int largestRectangleArea2(int[] heights) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i]; // 末尾补一个 0，把栈里剩余的全部逼出来结算
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];                   // 被弹出柱子的高度
                int width = stack.isEmpty() ? i : i - stack.peek() - 1; // 它能覆盖的宽度
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }


}
