package com.example.feedbacker.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.Duration;
import java.util.Properties;

@Configuration
public class AppConfig {

/*    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory cf) {
        RedisTemplate<String,String> tpl = new RedisTemplate<>();
        tpl.setConnectionFactory(cf);
        return tpl;
    }

    *//**
     * 配置线程池连接工厂LettuceConnectionFactory
     * @return
     *//*
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        // redis配置
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setDatabase(0);

        // 连接池配置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMinIdle(0);
        genericObjectPoolConfig.setMaxIdle(8);
        genericObjectPoolConfig.setMaxWait(Duration.ofDays(1));
        genericObjectPoolConfig.setMaxTotal(8);

        // ClientOptions配置
        ClientOptions clientOptions = ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).build();

        // redis客户端配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder =  LettucePoolingClientConfiguration
                .builder()
                .clientOptions(clientOptions)
                .commandTimeout(Duration.ofMillis(5000));

        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();
        return new LettuceConnectionFactory(redisStandaloneConfiguration,lettuceClientConfiguration);
    }*/

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setPort(587);
        sender.setUsername("490377187@qq.com");
        sender.setPassword("khhocvtqosxycafd");
        Properties p = sender.getJavaMailProperties();
        p.put("mail.transport.protocol", "smtp");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        return sender;
    }
}
