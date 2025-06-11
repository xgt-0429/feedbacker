package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.profile.UpdateProfileRequest;
import com.example.feedbacker.dto.response.profile.ProfileResponse;

public interface ProfileService {

    ProfileResponse getCurrentUserProfile();

    void updateCurrentUserProfile(UpdateProfileRequest req);

}
