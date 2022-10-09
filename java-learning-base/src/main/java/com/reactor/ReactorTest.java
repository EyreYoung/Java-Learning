package com.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j(topic = "Reactor测试")
public class ReactorTest {

    public static void main(String[] args) {

        // 创建Flux序列 声明数据流
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 5);
        // 订阅Flux序列 只有订阅后才会触发数据流
        integerFlux.subscribe(i -> log.info(String.valueOf(i)));

        Flux<String> stringFlux = Flux.just("Hello", "YYD");
        stringFlux.subscribe(log::info);

        Flux.fromArray(new Integer[]{1, 2, 3, 4}).subscribe(i -> log.info(String.valueOf(i)));

        Flux.fromIterable(Arrays.asList(1, 2, 3, 4)).subscribe(i -> log.info(String.valueOf(i)));

        Flux.fromStream(Stream.of(1, 2, 3, 4)).subscribe(i -> log.info(String.valueOf(i)));

    }

}
