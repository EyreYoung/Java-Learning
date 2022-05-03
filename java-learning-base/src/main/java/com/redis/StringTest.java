package com.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class StringTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "yyd");
        jedis.append("name", " no.1");
        String name = jedis.get("name");
        System.out.println(name);
    }

}
