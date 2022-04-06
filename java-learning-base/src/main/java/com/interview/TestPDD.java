package com.interview;

import java.util.HashSet;
import java.util.Scanner;


/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/1 6:53 下午
 */
public class TestPDD {
    public static void main(String[] args) {
        test4();
    }

    public static void test4(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] M = new int[m];
        int min = 1000000000;
        for(int i = 0; i < m; i++) {
            M[i] = in.nextInt();
            if(M[i] < min) {
                min = M[i];
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int cur = M[i];
            int j = 1;
            while (cur * j <= n) {
                set.add(cur * j);
                j++;
            }
        }
        System.out.println(set.size());


        int count = 0;
        for(int i = min; i <= n; i++) {
            for(int j = 0; j < m; j++) {
                if(i % M[j] == 0) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    public static void test3(){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int[] C = new int[N];
        int[] V = new int[N];
        for(int i = 0; i < N; i++) {
            C[i] = in.nextInt();
            V[i] = in.nextInt();
        }
        int[] dp = new int[M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = M; j >= C[i - 1]; j--) {
                dp[j] = Math.max(dp[j - C[i - 1]] + V[i - 1], dp[j]);
            }
        }
        System.out.println(dp[M]);
    }

    public static void test1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] A = new int[n][n];
        if (n % 2 == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n / 2) {
                        if (j < n / 2) {
                            if (i > j) {
                                A[i][j] = 3;
                            } else if (i < j) {
                                A[i][j] = 2;
                            }
                        } else {
                            if (i > n - 1 - j) {
                                A[i][j] = 8;
                            } else if (i < n - 1 - j) {
                                A[i][j] = 1;
                            }
                        }
                    } else {
                        if (j < n / 2) {
                            if (i > n - 1 - j) {
                                A[i][j] = 5;
                            } else if (i < n - 1 - j) {
                                A[i][j] = 4;
                            }
                        } else {
                            if (i > j) {
                                A[i][j] = 6;
                            } else if (i < j) {
                                A[i][j] = 7;
                            }
                        }
                    }
                }
            }
        }
        else {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i < n / 2) {
                        if(j < n / 2) {
                            if(i > j) {
                                A[i][j] = 3;
                            }
                            else if(i < j) {
                                A[i][j] = 2;
                            }
                        }
                        else if(j > n / 2) {
                            if(i > n-1-j) {
                                A[i][j] = 8;
                            }
                            else if(i < n-1-j) {
                                A[i][j] = 1;
                            }
                        }
                    }
                    else if(i > n / 2) {
                        if(j < n / 2) {
                            if(i > n-1-j) {
                                A[i][j] = 5;
                            }
                            else if(i < n-1-j) {
                                A[i][j] = 4;
                            }
                        }
                        else if(j > n / 2) {
                            if(i > j) {
                                A[i][j] = 6;
                            }
                            else if(i < j) {
                                A[i][j] = 7;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
