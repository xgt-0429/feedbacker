package com.example.feedbacker.utils;

import io.jsonwebtoken.*;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "ChangeThisSecretKey";
    private static final long EXP_MS = 1000L * 60 * 60 * 24; // 1 天

    public static String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(new Date(System.currentTimeMillis()+EXP_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Long parseUserId(String token) {
        Claims c = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return Long.valueOf(c.getSubject());
    }

    public static void blacklist(String token){
        try(Jedis redis = new Jedis("localhost",6379)){
            long exp = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token).getBody().getExpiration().getTime();
            long ttl = (exp - System.currentTimeMillis())/1000;
            if(ttl>0) redis.setex("bl:"+token, (int)ttl, "1");
        }
    }

    public static boolean isBlacklisted(String token){
        try(Jedis redis = new Jedis("localhost",6379)){
            return redis.exists("bl:"+token);
        }
    }
}