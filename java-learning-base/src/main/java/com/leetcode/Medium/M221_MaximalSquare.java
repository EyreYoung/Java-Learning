package com.leetcode.Medium;

public class M221_MaximalSquare {

    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
    }

    public static int maximalSquare(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        // dp[i][j]: 以matrix[i-1][j-1]为右下角的正方形最大边长是多少
        // 多一位是为了方便i=0或者j=0的计算
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= col; i++) {
            dp[0][i] = 0;
        }
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == '0') {
                    dp[i + 1][j + 1] = 0;
                } else {
                    // 转移公式：前三个元素的值中的最小值+1
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j], dp[i][j + 1]), dp[i + 1][j]) + 1;
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }
        return max * max;
    }

}
