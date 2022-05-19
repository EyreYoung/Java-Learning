package com.leetcode.Medium;

public class M866_PrimePalindrome {

    public static void main(String[] args) {
        System.out.println(new M866_PrimePalindrome().primePalindrome(12));
    }

    public int primePalindrome(int n) {
        String s = String.valueOf(n);
        boolean even = s.length() % 2 == 0;
        s = s.substring(0, s.length() / 2 + (even ? 0 : 1));
        int ret = make(s, even);
        int i = 1, base;
        while (ret <= n || !isPrime(ret)) {
            base = Integer.parseInt(s) + i;
            ret = make(String.valueOf(base), even);
            i++;
        }
        return ret;
    }

    public int make(String s, boolean even) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length() - (even ? 1 : 2); i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return Integer.parseInt(String.valueOf(sb));
    }

    public boolean isPrime(int n) {
        if (n < 2) return false;
        int nn = (int) Math.sqrt(n);
        for (int i = 2; i <= nn; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
