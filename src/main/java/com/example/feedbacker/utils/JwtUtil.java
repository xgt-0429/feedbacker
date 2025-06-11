package com.example.feedbacker.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {

    private static SecretKey SECRET_KEY;
    private static long EXPIRATION_MS;
    private static StringRedisTemplate redisTemplate;

    @Value("${jwt.secret}")
    private String jwtSecretBase64;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    // 注入 Spring 管理的 StringRedisTemplate
    public JwtUtil(StringRedisTemplate template) {
        JwtUtil.redisTemplate = template;
    }

    // 初始化静态字段
    @PostConstruct
    public void init() {
        // 解码 Base64 并生成 HS256 用的 SecretKey（≥256-bit）
        SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretBase64));
        EXPIRATION_MS = jwtExpirationMs;
    }

    /** 生成 JWT */
    public static String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /** 解析出用户 ID */
    public static Long parseUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
    }

    /** 注销：把 token 列入黑名单，TTL 到期自动失效 */
    public static void blacklist(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        long ttl = (claims.getExpiration().getTime() - System.currentTimeMillis()) / 1000;
        if (ttl > 0) {
            redisTemplate.opsForValue()
                    .set("bl:" + token, "1", Duration.ofSeconds(ttl));
        }
    }

    /** 检查 token 是否已被拉黑 */
    public static boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey("bl:" + token));
    }
}
