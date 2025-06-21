package com.example.feedbacker.controller;


import com.example.feedbacker.dto.request.auth.LoginRequest;
import com.example.feedbacker.dto.request.auth.RegisterRequest;
import com.example.feedbacker.dto.request.auth.SendCodeRequest;
import com.example.feedbacker.dto.response.ApiResponse;
import com.example.feedbacker.dto.response.auth.UserResponse;
import com.example.feedbacker.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.feedbacker.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService svc;
    public UserController(UserService svc){ this.svc = svc; }

    /**
     * 用户注册
     * @param req
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest req){
        Long id = svc.register(req);
        String token = JwtUtil.generateToken(id);
        return ResponseEntity.ok(new UserResponse(token));
    }

    /** 发送注册验证码 */
    @PostMapping("/send-code")
    public ResponseEntity<ApiResponse<Void>> sendCode(
            @Valid @RequestBody SendCodeRequest req) {
        svc.sendRegisterCode(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 用户登录
     * @param req
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest req){
        String token = svc.login(req);
        return ResponseEntity.ok(new UserResponse(token));
    }

    /**
     * 用户退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        if(auth!=null && auth.startsWith("Bearer ")){
            svc.logout(auth.substring(7));
        }
        return ResponseEntity.ok().build();
    }

}