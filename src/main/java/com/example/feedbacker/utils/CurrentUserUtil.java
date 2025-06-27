package com.example.feedbacker.utils;

public class CurrentUserUtil {
    private static final ThreadLocal<Long> TL = new ThreadLocal<>();
    public static void setUserId(Long id){ TL.set(id); }
    //public static Long getUserId(){ return TL.get(); }
    public static Long getUserId(){ return 6L; }
    public static void clear(){ TL.remove(); }
}