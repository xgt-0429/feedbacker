package com.example.feedbacker.dto.response.auth;


public class UserResponse {

    private final String token;

    public UserResponse(String token){ this.token = token; }

    public String getToken(){ return token; }

}
