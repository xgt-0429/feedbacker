package com.example.feedbacker.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequest {

    @Email
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
