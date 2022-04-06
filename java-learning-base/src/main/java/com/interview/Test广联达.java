package com.interview;

import java.util.Scanner;
import java.util.Timer;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/26 6:50 下午
 */
public class Test广联达 {

    static void test1(){
        Scanner in = new Scanner(System.in);
        String r = in.next();
        String t = in.next();
        int s = 0;
        for(int i = 0; i<r.length();i++){
            if(r.charAt(i)==(t.charAt(i))){
                s+=20;
            }
            else if(s>=10){
                s-=10;
            }
        }
        System.out.println(s);
    }

    static void test3(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for(int i = 0; i < A.length; i++){
            A[i] = sc.nextInt();
        }
        int m = 0;
        int max = Integer.MIN_VALUE;  // 初始化最小动数的值。
        for(int i = n - 1; i > 0; i--){
            if(max > A[i]){
                m++;  // 满足动数条件2
                continue;
            }
            for(int j = 0; j < i; j++){
                if(A[i]<A[j]){
                    m++;  // 满足动数条件1
                    max = A[i];  // 该动数与最小动数值比较
                    break;
                }
            }
        }
        System.out.println(m);
    }

    public static void main(String[] args) {
        test3();
    }
}


