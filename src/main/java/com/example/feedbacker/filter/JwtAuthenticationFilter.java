package com.example.feedbacker.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.example.feedbacker.utils.*;

public class JwtAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest h = (HttpServletRequest)req;
        String auth = h.getHeader("Authorization");
        if(auth!=null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            try {
                /*if(!JwtUtil.isBlacklisted(token)){
                    Long uid = JwtUtil.parseUserId(token);
                    CurrentUserUtil.setUserId(uid);
                }*/
            } catch(Exception e){ /* token 无效 or 过期 */ }
        }
        try {
            chain.doFilter(req, res);
        } finally {
            CurrentUserUtil.clear();
        }
    }
}