package com.ke.collections;

import java.util.*;

public class singleton {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(111);
        set.add("qwe");
        set.add(2.2);
        for (Iterator it = set.iterator(); it.hasNext();) {
            System.out.println("Value: " + it.next().toString());
        }

        String[] init = {"one", "two", "three", "four", "five", "six"};
        List list1 = new ArrayList(Arrays.asList(init));
        List list2 = new ArrayList(Arrays.asList(init));
        list1.remove("one");
        list1.add("one");
        System.out.println("list1 value: " + list1);

        list2.removeAll(Collections.singleton("one"));

        System.out.println(Collections.singleton("one"));
        System.out.println("list2 value: " + list2);
    }
}
