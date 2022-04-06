package com.interview;

import java.util.*;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/15 7:21 下午
 */
public class TestMi {
    public static void main(String[] args) {
        test1();
    }

    private static void test2() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Set<Character> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                list.add(s.charAt(i));
                set.add(s.charAt(i));
            }
        }
        for (Character c : list) {
            System.out.print(c);
        }
    }

    private static void test1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            boolean flag = true;
            Stack<Character> stack = new Stack<>();
            if(s.trim().isEmpty()){
                System.out.println(flag);
                continue;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '{' || c == '[' || c == '(') {
                    stack.push(c);
                }
                else if(c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                }
                else if(c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                }
                else if(c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
                else {
                    flag = false;
                    break;
                }
            }
            if (!stack.isEmpty()) {
                flag = false;
            }
            System.out.println(flag);
        }
    }
}
