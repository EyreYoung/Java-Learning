package com.core.generics;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/7/26
 */
public class Info<T> {

    private T value;

    public Info() {};

    public Info(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Info{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        Info<String> info = new Info<>("Gosh");
        System.out.println(info);
    }

}
