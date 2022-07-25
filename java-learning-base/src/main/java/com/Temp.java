package com;

/**
 * 一个工人未来一段时间有源源不断的工作量，每一天他都可以选择工作或者休息，但是他不能连续工作2天或者更多，即他工作一天后必须要休息。给定一个未来n天的工作量序列，替工人找到最优的工作时间安排（总工作量最大），返回总的工作量。
 * <p>
 * 示例 1：
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号工作和 3 号工作，总工作量 = 1 + 3 = 4。
 * <p>
 * 示例 2：
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号工作、 3 号工作和 5 号工作，总工作量 = 2 + 9 + 1 = 12。
 * <p>
 * 示例 3：
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号工作、 3 号工作、 5 号工作和 8 号工作，总工作量 = 2 + 4 + 3 + 3 = 12。
 */

public class Temp {

    public static void main(String[] args) {
        System.out.println(work(new int[]{2,1,4,5,3,1,1,3}));
    }

    public static int work(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp1 = new int[nums.length]; // 今天做了
        int[] dp2 = new int[nums.length]; // 今天没做
        dp1[0] = nums[0];
        dp2[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp1[i] = dp2[i - 1] + nums[i];
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
        }
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }

}
