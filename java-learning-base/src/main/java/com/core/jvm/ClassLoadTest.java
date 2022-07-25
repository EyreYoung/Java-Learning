package com.core.jvm;

import java.lang.reflect.Method;

public class ClassLoadTest {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.core.jvm.Person");
        Object o = clazz.newInstance();
        Method method = clazz.getMethod("max");
        method.invoke(o);
    }
 


}

class Person {
    public void max() {
        System.out.println("shit! You did it!");
    }
}
