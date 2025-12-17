package com.leetcode.Medium;

import java.util.ArrayList;
import java.util.List;

public class M93_RestoreIpAddresses {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("101023"));
    }

    public static List<String> ret = new ArrayList<>();

    public static StringBuilder sb = new StringBuilder();

    public static List<String> restoreIpAddresses(String s) {
        sb = new StringBuilder();
        restore(s, 0, 0);
        return ret;
    }

    public static void restore(String s, int pre, int x) {
        if (pre >= s.length()) return;
        if (x == 3) {
            if (s.length() - pre > 3) return;
            int i = Integer.parseInt(s.substring(pre, s.length() - 1));
            if (i == 0 && s.length() - pre != 1) return;
            if (i < 10 && s.length() - pre != 2) return;
            if (i < 100 && s.length() - pre != 2) return;
            ret.add(sb.toString());
            sb = new StringBuilder();
        }
        int i1 = Integer.parseInt(s.substring(pre, pre + 1));
        restore(s, pre + 1, x + 1);
        if (i1 == 0) return;
        if (s.length() - pre >= 2) {
            restore(s, pre + 2, x + 1);
        }
        if (s.length() - pre >= 3) {
            int i3 = Integer.parseInt(s.substring(pre, pre + 3));
            if (i3 <= 255) {
                restore(s, pre + 3, x + 1);
            }
        }
    }
}
