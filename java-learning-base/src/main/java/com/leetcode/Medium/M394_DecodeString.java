package com.leetcode.Medium;

public class M394_DecodeString {

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }

    public static String decodeString(String s) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            char c = s.charAt(i);
            if ('a' <= c && c <= 'z') {
                sb.append(c);
            } else if ('0' <= c && c <= '9') {
                int start = i;
                while (s.charAt(i) != '[') {
                    i++;
                }
                int end = i;
                int num = Integer.parseInt(s.substring(start, end));
                int left = 1;
                while (true) {
                    i++;
                    if (s.charAt(i) == '[') {
                        left++;
                    } else if (s.charAt(i) == ']') {
                        left--;
                        if(left == 0) break;
                    }
                }
                String next = decodeString(s.substring(end, i));
                sb.append(next.repeat(num));
            }
            i++;
        }
        return sb.toString();
    }

}
