package com.ke.collections;

import java.util.ArrayList;
import java.util.List;

public class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }
}
