package com.seu.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j(topic = "分布式锁测试")
public class distributedLock {
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private Jedis jedis = null;

    public distributedLock(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        JedisPool pool = new JedisPool(jedisPoolConfig, "101.37.89.158", 6379);
        try{
            jedis = pool.getResource();
            log.debug(jedis.set("testPool", "成功！"));
            log.debug(jedis.get("testPool"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static void main(String[] args) {

    }
}
