package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;
import com.example.feedbacker.dto.request.auth.SendCodeRequest;

public interface UserService {

    Long register(RegisterRequest req);

    void sendRegisterCode(SendCodeRequest req);

    String login(LoginRequest req);

    void logout(String token);

}
