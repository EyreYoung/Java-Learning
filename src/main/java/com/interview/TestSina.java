package com.interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/13 4:02 下午
 */
public class TestSina {
    public static void main(String[] args) {
        new son();
    }

    private static void test1() {
        Scanner in = new Scanner(System.in);
        String input  = in.nextLine();
        String[] ss = input.split(",");
        int[] nodes = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {

        }
    }

    public static void test2() {
        String ss = new Scanner(System.in).nextLine();
        char[] cs = ss.toCharArray();
        boolean flag = true;
        for (int i = 0; i < cs.length; i++) {
            if (!heapify(cs, i)) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);
    }

    private static boolean heapify(char[] cs, int i) {
        for (;i != 0;) {
            int p = (i - 1) / 2;
            if (cs[p] > cs[i]) break;
            else if (cs[p] == cs[i]) return false;
            else {
                swap(cs, p, i);
                i = p;
            }
        }
        return true;
    }

    private static void swap (char[] nums, int index1, int index2) {
        char temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    static class father {
        static {
            System.out.println("static father");
        }

        {
            System.out.println("non-static father");
        }

        public father(){
            System.out.println("constructor father");
        }
    }

    static class son extends father {
        static {
            System.out.println("static son");
        }

        {
            System.out.println("non-static son");
        }

        public son() {
            System.out.println("constructor son");
        }
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}