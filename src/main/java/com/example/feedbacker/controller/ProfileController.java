package com.example.feedbacker.controller;

import com.example.feedbacker.dto.request.profile.UpdateProfileRequest;
import com.example.feedbacker.dto.response.ApiResponse;
import com.example.feedbacker.dto.response.profile.ProfileResponse;
import com.example.feedbacker.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /** 查看当前登录用户资料 */
    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile() {
        ProfileResponse resp = profileService.getCurrentUserProfile();
        return ResponseEntity.ok(ApiResponse.success(resp));
    }

    /** 编辑当前登录用户资料 */
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateProfile(
            @Valid @RequestBody UpdateProfileRequest req) {
        profileService.updateCurrentUserProfile(req);
        return ResponseEntity.ok(ApiResponse.ok());
    }

}
