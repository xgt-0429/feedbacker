package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;

public interface UserService {

    Long register(RegisterRequest req);

    String login(LoginRequest req);

    void logout(String token);

}
