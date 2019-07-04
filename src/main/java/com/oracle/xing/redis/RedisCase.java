package com.oracle.xing.redis;

import redis.clients.jedis.Jedis;

/**
 * https://github.com/oraclexing
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 */
public class RedisCase {

    public static void main(String[] args){
        Jedis jedis = new Jedis("192.168.1.103");
        jedis.set("name","password");
        System.out.println("连接成功");
        // 查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }
}