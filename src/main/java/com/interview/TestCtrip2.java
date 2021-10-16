package com.interview;

import java.util.LinkedList;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/10/13 10:23 上午
 */
public class TestCtrip2 {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("a");
        list.add(2, "b");
        String s = (String) list.get(1);
        System.out.println(s);
    }
}
