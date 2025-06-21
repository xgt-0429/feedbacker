package com.example.feedbacker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password:}")
    private String password;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(host, port);
        if (!password.isBlank()) {
            factory.setPassword(password);
        }
        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory cf) {
        StringRedisTemplate tpl = new StringRedisTemplate(cf);
        // （可选）确保 key/value 都是 String
        tpl.setKeySerializer(new StringRedisSerializer());
        tpl.setValueSerializer(new StringRedisSerializer());
        tpl.afterPropertiesSet();
        return tpl;
    }
}
