package com.interview;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/23 3:35 下午
 */
public class TestIQIYI {
    public static void main(String[] args) {
        test3();
    }

    public static void test3(){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        if(s.length() == 0){
            System.out.println("True");
            return;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }
            else if(c == '}' && stack.peek() == '{') {
                stack.pop();
            }
            else if(c == ']' && stack.peek() == '[') {
                stack.pop();
            }
            else if(c == ')' && stack.peek() == '(') {
                stack.pop();
            }
            else {
                System.out.println("False");
                return;
            }
        }
        if (stack.isEmpty()) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
    }

    public int CountZero(int n) {
        int ret = 1;
        int count = 0;
        for(int i = 1; i <= n; i++) {
            ret *= i;
            while (ret % 10 == 0) {
                ret /= 10;
                count++;
            }
            if (ret > 10000) ret %= 10000;
        }
        return count;
    }

    public static void test2(){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int x = 0, y = 0;
        int len = s.length();
        int[] A = new int[len];
        int[] B = new int[len];
        for(int  i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c == 'N'){
                y++;
            }
            else if(c == 'S'){
                y--;
            }
            else if(c == 'E'){
                x++;
            }
            else if(c == 'W'){
                x--;
            }
            if(x == 0 && y == 0) {
                System.out.println("True");
                return;
            }
            for(int j = 0; j < i; j++) {
                if(A[j] == x && B[j] == y) {
                    System.out.println("True");
                    return;
                }
            }
            A[i] = x;
            B[i] = y;
        }
        System.out.println("False");
    }
}
