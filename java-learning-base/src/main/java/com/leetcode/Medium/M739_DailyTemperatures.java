package com.leetcode.Medium;


import java.util.Arrays;
import java.util.Stack;

/**
 * 739. 每日温度
 * <p>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class M739_DailyTemperatures {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); // [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30,40,50,60}))); // [1,1,1,0]
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30,60,90}))); // [1,1,0]
    }

    // 单调递减栈：栈里的下标对应的温度从底到顶递减
    // 遇到更高温度时，栈里所有比它矮的都"等到了答案"，逐个弹出结算
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>(); // 存下标，不存温度值
        for (int i = 0; i < temperatures.length; i++) {
            int x = temperatures[i];
            // 当前温度比栈顶高 → 栈顶那天等到了更高温度，弹出结算
            while (!stack.isEmpty() && x > temperatures[stack.peek()]) {
                int pop = stack.pop();
                ret[pop] = i - pop; // 等待天数 = 当前下标 - 栈顶下标
            }
            stack.push(i); // 当前下标入栈，等待未来更高温度来结算自己
        }
        // 栈里剩的都没等到更高温度，ret 默认值 0，不用处理
        return ret;
    }

}
