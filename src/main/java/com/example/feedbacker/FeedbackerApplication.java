package com.example.feedbacker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.feedbacker.mapper")
public class FeedbackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedbackerApplication.class, args);
    }

}
