package com.example.feedbacker.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class SendCodeRequest {
    @Email @NotBlank public String email;
}
