package com.interview;

import java.util.*;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/22 7:03 下午
 */

public class Test360{
    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        for(int i = 0; i < n; i++) {
            String s = in.next();
            if(check(s))
                count++;
        }
        System.out.println(count);
    }

    public static boolean check(String s) {
        System.out.println(s + " 's  = " + s.length());
        if(s.length() > 10) return false;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isLetter(s.charAt(i))) return false;
        }
        return true;
    }

    public static void test2() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            l.add(i + 1);
        }
        for(int i = 0; i < M; i++) {
            int x = in.nextInt();
            if (1 == x) {
                l = trans1(l);
            }
            if (2 == x) {
                l = trans2(l);
            }

        }
        System.out.println(l);
    }

    public static List<Integer> trans1(List<Integer> A) {
        A.add(A.remove(0));
        return A;
    }

    public static List<Integer> trans2(List<Integer> A) {
        Integer temp;
        for (int i = 0; i < A.size(); i+=2) {
            temp = A.get(i);
            A.set(i, A.get(i + 1));
            A.set(i + 1, temp);
        }
        return A;
    }
}