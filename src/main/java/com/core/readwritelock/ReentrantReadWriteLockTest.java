package com.core.readwritelock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author slowdive
 * @summary 利用ReentrantReadWriteLock构建线程安全的泛型无界缓存
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/10
 */

@Slf4j(topic = "读写锁测试")
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executorService.execute(new MyThread());
            cache.get(0);
        }
        executorService.shutdown();
    }

    static UnboundedCache<Integer, Integer> cache = new UnboundedCache<>();

    static class MyThread implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                cache.put(i, random.nextInt(1000));
            }
        }
    }

    static class UnboundedCache<K, V> {

        private final Map<K, V> cacheMap = new WeakHashMap<>();

        private final ReadWriteLock cacheLock = new ReentrantReadWriteLock();

        public V get(K key) {
            cacheLock.readLock().lock();
            V value;
            try {
                value = cacheMap.get(key);
                log.info("{} 读数据 {}: {}", Thread.currentThread().getName(), key, value);
            } finally {
                cacheLock.readLock().unlock();
            }
            return value;
        }

        public V put(K key, V value) {
            cacheLock.writeLock().lock();
            try {
                cacheMap.put(key, value);
                log.info("{} 写数据 {}: {}", Thread.currentThread().getName(), key, value);
            } finally {
                cacheLock.writeLock().unlock();
            }
            return value;
        }

        public V remove(K key) {
            cacheLock.writeLock().lock();
            try {
                return cacheMap.remove(key);
            } finally {
                cacheLock.writeLock().unlock();
            }
        }

        public void clear() {
            cacheLock.writeLock().lock();
            try {
                cacheMap.clear();
            } finally {
                cacheLock.writeLock().unlock();
            }
        }

    }

}
