package com.reactor;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;

@Slf4j(topic = "Java 8 实现观察者模式")
public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();
        // 添加观察者
        observerDemo.addObserver((o, arg) -> {
            log.info("数据发生变化A");
        });
        observerDemo.addObserver((o, arg) -> {
            log.info("数据发生变化B");
        });
        observerDemo.setChanged(); // 标记该对象为已更改
        observerDemo.notifyObservers();
    }

}
