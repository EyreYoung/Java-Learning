package com.core.generics;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/7/26
 */
public class ContentDemo1 implements Content<Integer> {

    private final int text;

    public ContentDemo1(int text) {
        this.text = text;
    }

    @Override
    public Integer text() {
        return text;
    }

    public static <T> String whatClass(T obj) {
        return obj.getClass().getName();
    }


    public static void main(String[] args) {
        ContentDemo1 demo1 = new ContentDemo1(100);
        System.out.println(demo1.text);
        System.out.println(whatClass('c'));
    }

}
