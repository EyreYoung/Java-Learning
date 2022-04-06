package com.leetcode;

import java.util.*;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2021, Lianjia Group All Rights Reserved.
 * @since 2021/10/11
 */
public class Hard273 {
    public static void main(String[] args) {
        System.out.println(new Hard273().numberToWords(10000001));
    }

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        List<Integer> nums = new ArrayList<>();
        while (num > 0) {
            nums.add(num % 10);
            num = num / 10;
        }
        int tmb = (nums.size() + 2) / 3;
        Map<Integer, String> numberMap = new HashMap<>();
        numberMap.put(0, "");
        numberMap.put(1, "One");
        numberMap.put(2, "Two");
        numberMap.put(3, "Three");
        numberMap.put(4, "Four");
        numberMap.put(5, "Five");
        numberMap.put(6, "Six");
        numberMap.put(7, "Seven");
        numberMap.put(8, "Eight");
        numberMap.put(9, "Nine");
        numberMap.put(10, "Ten");
        numberMap.put(11, "Eleven");
        numberMap.put(12, "Twelve");
        numberMap.put(13, "Thirteen");
        numberMap.put(14, "Fourteen");
        numberMap.put(15, "Fifteen");
        numberMap.put(16, "Sixteen");
        numberMap.put(17, "Seventeen");
        numberMap.put(18, "Eighteen");
        numberMap.put(19, "Nineteen");
        numberMap.put(20, "Twenty");
        numberMap.put(30, "Thirty");
        numberMap.put(40, "Forty");
        numberMap.put(50, "Fifty");
        numberMap.put(60, "Sixty");
        numberMap.put(70, "Seventy");
        numberMap.put(80, "Eighty");
        numberMap.put(90, "Ninety");
        numberMap.put(100, "Hundred");
        numberMap.put(1000, "Thousand");
        numberMap.put(1000000, "Million");
        numberMap.put(1000000000, "Billion");
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < tmb; i++) {
            StringBuilder sb = new StringBuilder();
            int 个位 = i * 3 > nums.size() - 1 ? 0 : nums.get(i * 3);
            int 十位 = i * 3 + 1 > nums.size() - 1 ? 0 : nums.get(i * 3 + 1);
            int 百位 = i * 3 + 2 > nums.size() - 1 ? 0 : nums.get(i * 3 + 2);
            if (个位 + 十位 * 10 + 百位 * 100 != 0) {
                if (百位 != 0) {
                    sb.append(numberMap.get(百位)).append(" ").append(numberMap.get(100));
                }
                if ((个位 + 十位 * 10) != 0) {
                    sb.append(" ");
                }
                if ((个位 + 十位 * 10) > 20) {
                    sb.append(numberMap.get(十位 * 10)).append(" ").append(numberMap.get(个位));
                }
                else {
                    sb.append(numberMap.get(个位 + 十位 * 10));
                }
                sb = new StringBuilder(sb.toString().trim());
                if (i == 1) {
                    sb.append(" ").append(numberMap.get(1000)).append(" ");
                }
                else if (i == 2) {
                    sb.append(" ").append(numberMap.get(1000000)).append(" ");
                }
                else if (i == 3) {
                    sb.append(" ").append(numberMap.get(1000000000)).append(" ");
                }
                ret.add(sb.toString());
            }
        }
        StringBuilder nStr = new StringBuilder();
        for (int i = ret.size() - 1; i >= 0; i--) {
            nStr.append(ret.get(i));
        }
        return nStr.toString().trim();
    }
}
