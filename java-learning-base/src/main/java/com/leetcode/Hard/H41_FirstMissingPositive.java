package com.leetcode.Hard;

import java.util.Arrays;

public class H41_FirstMissingPositive {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{7,8,9,11,12})); // 1
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1})); // 2
        System.out.println(firstMissingPositive(new int[]{1,2,0})); // 3
        System.out.println(firstMissingPositive(new int[]{1,1,1})); // 2
    }

    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        // 符合条件的正整数一定在[1, len+1]这个范围里
        // 遍历一遍，把所有在这个范围内的元素放到按顺序的位置上
        for (int i = 0; i < len; i++) {
            int x = nums[i];
            if (1 <= x && x <= len) {
                // 在这个范围内，且目标位置的值不对，就把两个元素对调
                if (nums[x - 1] != x) {
                    nums[i] = nums[x - 1];
                    nums[x - 1] = x;
                    i--; // 对调之后还得检查下换过来的元素，循环继续坚持当前元素
                // 如果目标位置的值是对的，把当前元素置为-1，防止重复
                } else if (x - 1 != i) {
                    nums[i] = -1;
                }
            } else {
                // 不在这个范围的话就置为-1，因为没用了
                nums[i] = -1;
            }
        }
        // 循环一遍找到第一个空的位置，直接返回
        for (int i = 0; i < len; i++) {
            if (nums[i] == -1) {
                return i + 1;
            }
        }
        // 所有位置都占满了就返回最后一个值+1
        return len + 1;
    }

}
