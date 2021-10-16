package com.ke.serializable;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j(topic = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String address;

    public Person() {
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("person.out");
        // 序列化
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person("yyd", 22, "上海");
        oout.writeObject(person);
        oout.close();
        // 反序列化
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject();
        oin.close();
        log.debug(newPerson.toString());
    }
}
