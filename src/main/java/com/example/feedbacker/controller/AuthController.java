package com.example.feedbacker.controller;


import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;
import com.example.feedbacker.dto.response.auth.AuthResponse;
import com.example.feedbacker.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.feedbacker.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService svc;
    public AuthController(UserService svc){ this.svc = svc; }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req){
        Long id = svc.register(req);
        String token = JwtUtil.generateToken(id);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req){
        String token = svc.login(req);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        if(auth!=null && auth.startsWith("Bearer ")){
            svc.logout(auth.substring(7));
        }
        return ResponseEntity.ok().build();
    }
}