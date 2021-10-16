package com.seu.list;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/24 11:27 上午
 */
public class LearnList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        System.out.println(list.get(0));

    }
}
