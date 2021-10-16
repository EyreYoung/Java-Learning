package com.ke.collections;

import java.util.function.BiFunction;
import java.util.function.Function;

public class bifunction {

    public double compute1(double a, double b, BiFunction<Double, Double, Double> biFunction) {
        return biFunction.apply(a, b);
    }

    public int compute2(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }

    public static void main(String[] args) {
        bifunction b = new bifunction();

        System.out.println("b.compute(4, 3, (v1, v2) -> v1 / v2) = " + b.compute1(4, 3, (v1, v2) -> (v1 / v2)));

        System.out.println("b.compute2(4, 3, Integer::sum, v -> v * v) = " + b.compute2(4, 3, Integer::sum, v -> v * v));
    }
}
