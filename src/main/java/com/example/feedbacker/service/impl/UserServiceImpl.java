package com.example.feedbacker.service.impl;

import java.time.LocalDateTime;

import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedbacker.entity.Users;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.UsersMapper;
import com.example.feedbacker.service.UserService;
import com.example.feedbacker.utils.*;


@Service
public class UserServiceImpl implements UserService {
    private final UsersMapper userMapper;
    public UserServiceImpl(UsersMapper m){ this.userMapper = m; }

    @Override @Transactional
    public Long register(RegisterRequest req){
        if(userMapper.selectByUserName(req.getUsername())!=null){
            throw new ApiException("用户名已存在");
        }
        Users u = new Users();
        u.setUsername(req.getUsername());
        u.setPasswordHash(PasswordUtil.hash(req.getPassword()));
        u.setEmail(req.getEmail());
        u.setCreatedAt(LocalDateTime.now());
        u.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(u);
        return u.getId();
    }

    @Override
    public String login(LoginRequest req){
        Users u = userMapper.selectByUserName(req.getUsername());
        if(u==null || !PasswordUtil.matches(req.getPassword(), u.getPasswordHash())){
            throw new ApiException("用户名或密码错误");
        }
        String token = JwtUtil.generateToken(u.getId());
        return token;
    }

    @Override
    public void logout(String token){
        // 简单做法：把 token 加入 Redis 黑名单
        JwtUtil.blacklist(token);
    }
}