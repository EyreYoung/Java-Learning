package com;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/15
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton() {}

    public Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;

    }

    public static int maxSum(int[] nums) {
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            max = Math.max(dp, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

}
