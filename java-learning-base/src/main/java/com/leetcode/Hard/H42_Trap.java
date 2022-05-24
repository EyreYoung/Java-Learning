package com.leetcode.Hard;

import java.util.Stack;

/**
 * 接雨水
 */

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 示例 1：
//
//
//
//
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//
//
// 示例 2：
//
//
//输入：height = [4,2,0,3,2,5]
//输出：9
//
//
//
//
// 提示：
//
//
// n == height.length
// 1 <= n <= 2 * 10⁴
// 0 <= height[i] <= 10⁵
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 3468 👎 0

public class H42_Trap {

    public static void main(String[] args) {
        System.out.println(new H42_Trap().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new H42_Trap().trap(new int[]{4,2,0,3,2,5}));
    }

    public int trap(int[] height) {
        // 特殊情况
        if (height.length <= 2) return 0;

        int ret = 0;
        // 单调栈 （栈顶元素最小）
        // 注意：栈内存放的是元素下标 i ，而不是元素值，为了计算雨水面积时好计算宽度
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                // 如果栈顶元素小于当前遍历的 i，将其pop出，并拿 (其左边元素和当前元素之中的最小值) * (当前元素下表 - 左边元素下标)
                int peek = stack.peek();
                // 栈顶元素相同的一起pop出，一起计算
                while (!stack.isEmpty() && height[stack.peek()] == height[peek]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    ret += (Math.min(height[stack.peek()], height[i]) - height[peek]) * (i - stack.peek() - 1);
                }
                // pop出一个(或多个相同的)比元素i小的栈顶元素后，如果后面的栈顶元素仍然小于元素i，继续重复
            }
            // 无论当前元素比栈顶大(大了的话已经进行了处理)还是小，都要push进去，因为此时元素i一定是栈内最小的
            stack.push(i);
        }
        return ret;
    }

}
