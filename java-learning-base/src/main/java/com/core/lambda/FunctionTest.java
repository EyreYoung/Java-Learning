package com.core.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/24
 */

public class FunctionTest<T> {

    private T t;

    void set(T t) {
        this.t = t;
    }



    public static void main(String[] args) {
        Function<String, Integer> fun2 = s -> {
            return Integer.valueOf(s);
        };

    }
}
