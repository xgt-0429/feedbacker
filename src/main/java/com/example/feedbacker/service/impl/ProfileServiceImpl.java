package com.example.feedbacker.service.impl;

import com.example.feedbacker.dto.request.profile.UpdateProfileRequest;
import com.example.feedbacker.dto.response.profile.ProfileResponse;
import com.example.feedbacker.entity.Profile;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.ProfileMapper;
import com.example.feedbacker.service.ProfileService;
import com.example.feedbacker.utils.CurrentUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileResponse getCurrentUserProfile() {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) {
            throw new ApiException("未登录");
        }
        Profile p = profileMapper.selectByUserId(userId);
        if (p == null) {
            throw new ApiException("用户资料不存在");
        }
        return new ProfileResponse(
                p.getUserId(),
                p.getDisplayName(),
                p.getAvatarUrl(),
                p.getBio()
        );
    }

    @Override
    @Transactional
    public void updateCurrentUserProfile(UpdateProfileRequest req) {
        Long userId = CurrentUserUtil.getUserId();
        if (userId == null) {
            throw new ApiException("未登录");
        }
        Profile p = new Profile();
        p.setUserId(userId);
        p.setDisplayName(req.getDisplayName());
        p.setAvatarUrl(req.getAvatarUrl());
        p.setBio(req.getBio());
        int updated = profileMapper.updateByUserId(p);
        if (updated == 0) {
            throw new ApiException("更新失败，资料不存在");
        }
    }

}
