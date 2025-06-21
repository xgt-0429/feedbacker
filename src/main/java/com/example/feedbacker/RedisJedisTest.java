package com.example.feedbacker;

import redis.clients.jedis.Jedis;

public class RedisJedisTest {
    public static void main(String[] args) {
        // 改成你的 EC2 公网 IP 或 DNS
        String host = "13.210.204.61";
        int port    = 6379;

        try (Jedis jedis = new Jedis(host, port)) {
            // 测试 PING/PONG
            System.out.println("PING → " + jedis.ping());

            // 简单写入/读取
            jedis.set("demo:key", "Hello from Local Java!");
            System.out.println("GET demo:key → " + jedis.get("demo:key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
