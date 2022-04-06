package com.ke.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j(topic = "Stream学习")
public class stream {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("aa", "bbb", " ", "c", "dd", "", "eee");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(7, 5, 2, 3, 6, 0, 8);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

        System.out.println(strings.stream().filter(string -> string.length() > 1).count());

        List<String> stringss = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println(stringss.parallelStream().filter(String::isEmpty).count());

        IntSummaryStatistics statistics = numbers.stream().mapToInt((x) -> (x)).summaryStatistics();
        System.out.println("平均数：" + statistics.getAverage());

        List<Person> persons = Arrays.asList(new Person("yyd", 18), new Person("csy", 20), new Person("lc", 17));

        Map<Short, List<Person>> collect = persons
                .stream()
                .collect(Collectors.groupingBy(person -> person.age));

        collect.forEach((age, p) -> System.out.format("Age: %s, %s\n", age, p));

        System.out.println("persons.stream().collect(Collectors.averagingInt(p -> p.age)) = " + persons.stream().collect(Collectors.averagingInt(p -> p.age)));

        System.out.println("persons.stream().collect(Collectors.summarizingInt(p -> p.age)) = " + persons.stream().collect(Collectors.summarizingInt(p -> p.age)));

        String joining = persons.stream().filter(p -> p.age >= 18).map(p -> p.name).collect(Collectors.joining(" and ", "In Germany, ", " are of legal age."));

        System.out.println(joining);

        Collector<Person, StringJoiner, String> stringJoinerSupplier = Collector.of(
                () -> new StringJoiner(" | "),
                (j, p) -> j.add(p.name.toUpperCase()),
                StringJoiner::merge,
                StringJoiner::toString);

        System.out.println("persons.stream().collect(stringJoinerSupplier) = " + persons.stream().collect(stringJoinerSupplier));

        List<Foo> foos = new ArrayList<>();
        IntStream
                .range(1, 4)
                .forEach(i ->
                        foos.add(new Foo("foo" + i)));
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i ->
                                f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(bar -> System.out.println(bar.name));

        System.out.println("Stream.of(\"ss\", \"aa\", \"cc\").collect(Collectors.toList()) = " + Stream.of("ss", "aa", "cc").collect(Collectors.toList()));

        // 并行对stream执行函数
        System.out.println("Stream.of(function1, function2, function3).parallel().flatMap(f -> f.apply(100, 200).stream()).collect(Collectors.toSet()) = "
                + Stream.of(function1, function2, function3)
                .parallel()
                .flatMap(f -> f.apply(100, 200).stream())
                .collect(Collectors.toSet()));

    }

    private static BiFunction<Integer, Integer, Set<String>> function1 = (a, b) -> {
        Set<String> set = new HashSet<>();
        set.add(a.toString());
        set.add(a.toString() + "*" + b.toString());
        set.add(b.toString());
        return set;
    };

    private static BiFunction<Integer, Integer, Set<String>> function2 = (a, b) -> {
        Set<String> set = new HashSet<>();
        set.add(a.toString() + "(copy2)");
        set.add(a.toString() + "*" + b.toString() + "(copy2)");
        set.add(b.toString() + "(copy2)");
        return set;
    };

    private static BiFunction<Integer, Integer, Set<String>> function3 = (a, b) -> {
        Set<String> set = new HashSet<>();
        set.add(a.toString() + "(copy3)");
        set.add(a.toString() + "*" + b.toString() + "(copy3)");
        set.add(b.toString() + "(copy3)");
        return set;
    };

}
