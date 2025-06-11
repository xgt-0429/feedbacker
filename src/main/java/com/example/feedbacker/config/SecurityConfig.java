package com.example.feedbacker.config;

import org.springframework.context.annotation.*;
import com.example.feedbacker.filter.JwtAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 把你的 JwtAuthenticationFilter 注入到 Spring 容器
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // 核心的安全策略配置
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                // 关闭 CSRF，因为我们用 JWT，无状态
                .csrf(AbstractHttpConfigurer::disable)
                // 不创建 Session
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 配置 URL 访问权限
                .authorizeHttpRequests(auth -> auth
                                // 放行注册、登录、登出接口
                                //.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                // 其余所有 /api/** 都得认证
                                //.requestMatchers("/api/**").authenticated()
                        // （如有其它无需鉴权的接口再继续 permitAll()）
                )
                // 把我们的 JWT 过滤器加在 UsernamePasswordAuthenticationFilter 前面
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}