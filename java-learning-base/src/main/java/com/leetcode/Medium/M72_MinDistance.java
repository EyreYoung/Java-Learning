package com.leetcode.Medium;

public class M72_MinDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }

    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // dp[i][j] 代表 word1 中前 i 个字符，变换到 word2 中前 j 个字符，最短需要操作的次数
        dp[0][0] = 0;
        for (int i = 0; i <= word1.length() ; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {

                int replace = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                int add = dp[i - 1][j] + 1;
                int remove = dp[i][j - 1] + 1;
                dp[i][j] = Math.min(replace, Math.min(add, remove));
            }
        }
        return dp[word1.length()][word2.length()];
    }

}
