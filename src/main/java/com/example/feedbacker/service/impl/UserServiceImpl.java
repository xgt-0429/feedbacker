package com.example.feedbacker.service.impl;

import java.time.LocalDateTime;

import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;
import com.example.feedbacker.entity.Profile;
import com.example.feedbacker.mapper.ProfileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedbacker.entity.User;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.UserMapper;
import com.example.feedbacker.service.UserService;
import com.example.feedbacker.utils.*;


@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;

    public UserServiceImpl(UserMapper userMapper,
                           ProfileMapper profileMapper) {
        this.userMapper = userMapper;
        this.profileMapper = profileMapper;
    }

    @Override @Transactional
    public Long register(RegisterRequest req){
        if(userMapper.selectByUserName(req.getUsername())!=null){
            throw new ApiException("用户名已存在");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPasswordHash(PasswordUtil.hash(req.getPassword()));
        u.setEmail(req.getEmail());
        u.setCreatedAt(LocalDateTime.now());
        u.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(u);

        // 2. 初始化用户资料（Profile）
        Profile p = new Profile();
        p.setUserId(u.getId());
        // 昵称自动生成：例如 “User” + 用户ID
        p.setDisplayName("User" + u.getId());
        p.setAvatarUrl(null); // 允许为空
        p.setBio(null);       // 允许为空
        profileMapper.insert(p);

        return u.getId();
    }

    @Override
    public String login(LoginRequest req){
        User u = userMapper.selectByUserName(req.getUsername());
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