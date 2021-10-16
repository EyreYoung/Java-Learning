package com.interview;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/1 6:52 下午
 */
public class TestHuawei {
    public static void main(String[] args) {
        int[] A = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(GetSubArraySum(A, A.length));
    }

    public static int GetSubArraySum(int[] A, int n) {
        int[] dp = new int[n];
        dp[0] = A[0];
        int max = A[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = A[i];
            }
            else {
                dp[i] = dp[i - 1] + A[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
