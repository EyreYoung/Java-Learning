package com.core.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author slowdive
 * @summary LRU缓存，自动删除最老的数据
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/15
 */

@Slf4j
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(4);
        log.info(String.valueOf(cache.size()));
        cache.put("first", 1);
        log.info(String.valueOf(cache.size()));
        cache.put("second", 2);
        log.info(String.valueOf(cache.size()));
        cache.put("third", 3);
        log.info(String.valueOf(cache.size()));
        cache.put("fourth", 4);
        log.info(String.valueOf(cache.size()));
        cache.put("fifth", 5);
        log.info(String.valueOf(cache.size()));
    }

}
