package com.ke.collections;

public class Person {
    public String name;
    public short age;

    public Person(String name, int age) {
        this.name = name;
        this.age = (short) age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
