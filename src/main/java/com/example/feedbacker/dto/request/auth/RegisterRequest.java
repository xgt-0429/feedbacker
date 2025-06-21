package com.example.feedbacker.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @Email
    @NotBlank private String username;

    @NotBlank @Size(min=6,max=6)
    public String code;

    @Size(min=8)
    @NotBlank private String password;

}
