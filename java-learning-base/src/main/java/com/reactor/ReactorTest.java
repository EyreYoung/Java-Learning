package com.reactor;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

@Slf4j(topic = "Reactor测试")
public class ReactorTest {

    public static void main(String[] args) {

        Stopwatch sw = Stopwatch.createStarted();


        log.info("---Flux创建方式---");
        // 创建Flux序列 声明数据流
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 5);
        // 订阅Flux序列 只有订阅后才会触发数据流
        integerFlux.subscribe(i -> log.info("Flux-just-subscribe: " + i));

        Flux<String> stringFlux = Flux.just("Hello", "YYD");
        stringFlux.subscribe(log::info);

        Flux.fromArray(new Integer[]{1, 2, 3, 4}).subscribe(i -> log.info("Flux-fromArray: " + i));

        Flux.fromIterable(Arrays.asList(1, 2, 3, 4)).subscribe(i -> log.info("Flux-fromIterable: " + i));

        Flux.fromStream(Stream.of(1, 2, 3, 4)).subscribe(i -> log.info("Flux-fromStream: " + i));

        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(i -> log.info("Flux-interval: " + i));

        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(i -> log.info("Flux-generate: " + i));

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int v = random.nextInt(100);
            list.add(v);
            sink.next(v);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(i -> log.info("Flux-generate: " + i));

        log.info("Flux create可以在一次调用中产生多个元素");
        Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).subscribe(i -> log.info("Flux-create: " + i));

        Flux.range(1, 1000).takeWhile(i -> i % 10 == 0).subscribe(i -> log.info("Flux-takeWhile: " + i));

        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(i -> log.info("Flux-reduce: " + i));
        Flux.range(1, 10).reduceWith(() -> 1, (x, y) -> x * y).subscribe(i -> log.info("Flux-reduceWith: " + i));

        Flux.merge(
                Flux.interval(
                        Duration.of(0, ChronoUnit.SECONDS),
                        Duration.of(3, ChronoUnit.SECONDS)).take(5),
                Flux.interval(
                        Duration.of(1, ChronoUnit.SECONDS),
                        Duration.of(3, ChronoUnit.SECONDS)).take(5))
                .toStream().forEach(i -> log.info("Flux-merge: " + i));

        Flux.mergeSequential(
                        Flux.interval(
                                Duration.of(0, ChronoUnit.SECONDS),
                                Duration.of(3, ChronoUnit.SECONDS)).take(5),
                        Flux.interval(
                                Duration.of(1, ChronoUnit.SECONDS),
                                Duration.of(3, ChronoUnit.SECONDS)).take(5))
                .toStream().forEach(i -> log.info("Flux-mergeSequential: " + i));

        log.info("---流式数据处理---");
        ReactorTest reactorTest = new ReactorTest();
        reactorTest.allStreamUsers().forEach(cu -> log.info(cu.toString()));

        log.info("---反应式数据处理---");
        reactorTest.allFluxUsers().subscribe(cu -> log.info(cu.toString()));

        log.info("---Mono---");
        Mono<ClientUser> data = Mono.just(new ClientUser("mono.com", "Mono"));
        Mono<Object> noData = Mono.empty();
        data.subscribe(i -> log.info("Mono - " + i));

        sw.stop();
        log.info(sw.toString());

    }

    public Stream<ClientUser> allStreamUsers() {
        return Stream.of(new ClientUser("be.com", "reactive"), new ClientUser("fe", "REACTOR"));
    }

    public Flux<ClientUser> allFluxUsers() {
        return Flux.just(new ClientUser("be.com", "reactive"), new ClientUser("fe", "REACTOR"));
    }

}
