package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2021, Lianjia Group All Rights Reserved.
 * @since 2021/10/13
 */
public class Easy412 {
    public static void main(String[] args) {
        System.out.println(new Easy412().fizzBuzz(20));
    }
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (i % 3 != 0 && i % 5 != 0){
                sb.append(i);
            }
            ret.add(sb.toString());
        }
        return ret;
    }
}
