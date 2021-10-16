package com.ke.collections;

import java.util.function.Function;

public class function {
    public int compute1(int a, Function<Integer, Integer> function) {
        return function.apply(a);
    }

    // compose表示先执行作为参数的function，将结果作为调用的函数的输入
    public int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2, Function<Integer, Integer> function3) {
        return function1.compose(function2.compose(function3)).apply(a);
    }

    // andThen表示先执行apply，再将结果传给作为参数的函数
    public int compute3(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public static void main(String[] args) {
        function function = new function();
        System.out.println("function.compute(5, value -> value * 2) = " + function.compute1(5, value -> value * 2));

        System.out.println("function.compute2(5, v -> v + 2, v -> v * 2, v -> v + 4) = " + function.compute2(5, v -> v + 2, v -> v * 2, v -> v + 4));

        System.out.println("function.compute3(5, v -> v + 2, v -> v * v) = " + function.compute3(5, v -> v + 2, v -> v * v));

    }
}
