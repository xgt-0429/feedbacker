package com.example.feedbacker.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder ENC = new BCryptPasswordEncoder();
    public static String hash(String raw){ return ENC.encode(raw); }
    public static boolean matches(String raw, String hashed){ return ENC.matches(raw, hashed); }
}