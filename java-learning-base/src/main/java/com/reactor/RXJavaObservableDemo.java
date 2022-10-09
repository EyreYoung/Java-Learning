package com.reactor;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "RXJava测试")
public class RXJavaObservableDemo {

    public static void main(String[] args) {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for (int i = 0; i < 5; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });

        // observer订阅observable
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                log.info("订阅时触发");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                log.info("接收Observable中发射的值: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log.error("发生异常");
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
