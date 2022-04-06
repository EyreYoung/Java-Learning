package com.core.javaclass;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/18
 */

public class ParentImpl implements Parent {
    public static void main(String[] args) {
        Child parent = (Child) new ParentImpl();
        System.out.println(parent.edu("", 1.1));
        Child child = new ChildImpl();
        System.out.println(child.edu("", 2.2));
    }
}
