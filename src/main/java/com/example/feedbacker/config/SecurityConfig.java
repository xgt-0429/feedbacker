package com.example.feedbacker.config;

import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.*;
import com.example.feedbacker.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter(){
        FilterRegistrationBean<JwtAuthenticationFilter> f = new FilterRegistrationBean<>();
        f.setFilter(new JwtAuthenticationFilter());
        f.addUrlPatterns("/api/*");     // 鉴权所有 API，可根据需要排除 auth/login/register
        f.setOrder(1);
        return f;
    }
}