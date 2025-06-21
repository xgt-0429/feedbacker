package com.example.feedbacker.service.impl;

import java.time.Duration;
import java.util.Random;

import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;
import com.example.feedbacker.dto.request.auth.SendCodeRequest;
import com.example.feedbacker.entity.Profile;
import com.example.feedbacker.mapper.ProfileMapper;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    RedisTemplate<String,String> redis;
    JavaMailSender mailSender;

    public UserServiceImpl(
                           JavaMailSender mailSender,
                           UserMapper userMapper,
                           ProfileMapper profileMapper,
                           RedisTemplate<String,String> redis) {
        this.mailSender = mailSender;
        this.userMapper = userMapper;
        this.profileMapper = profileMapper;
        this.redis = redis;
    }

    @Override @Transactional
    public Long register(RegisterRequest req) {
        String email = req.getUsername().toLowerCase();
        String key = "reg:code:" + email;
        String saved = redis.opsForValue().get(key);
        if (saved == null) {
            throw new ApiException("验证码已过期，请重新获取");
        }
        if (!saved.equals(req.code)) {
            throw new ApiException("验证码错误");
        }
        // 删除验证码
        redis.delete(key);

        // 查重
        if (userMapper.selectByEmail(email) != null) {
            throw new ApiException("邮箱已注册");
        }

        // 1. 创建 User
        User u = new User();
        u.setUsername(email);          // 以 email 作为 username
        u.setEmail(email);
        u.setPasswordHash(PasswordUtil.hash(req.getPassword()));
        u.setCreatedAt(java.time.LocalDateTime.now());
        u.setUpdatedAt(u.getCreatedAt());
        userMapper.insert(u);

        // 2. 创建 Profile
        Profile p = new Profile();
        p.setUserId(u.getId());
        p.setDisplayName(email);
        p.setAvatarUrl(null);
        p.setBio(null);
        profileMapper.insert(p);

        return u.getId();
    }

    @Override
    public void sendRegisterCode(SendCodeRequest req) {
        String email = req.email.toLowerCase();
        // 限制：同一邮箱5分钟内只能发一次
        String key = "reg:code:" + email;
        if (Boolean.TRUE.equals(redis.hasKey(key))) {
            throw new ApiException("验证码已发送，请稍后重试");
        }
        // 生成 6 位数字
        String code = String.format("%06d", new Random().nextInt(1_000_000));
        // 存 Redis 5 分钟过期
        redis.opsForValue().set(key, code, Duration.ofMinutes(5));

        // 发送邮件
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("490377187@qq.com");
        msg.setSubject("您的注册验证码");
        msg.setText("您本次注册验证码为：" + code + "，5 分钟内有效。");
        mailSender.send(msg);
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