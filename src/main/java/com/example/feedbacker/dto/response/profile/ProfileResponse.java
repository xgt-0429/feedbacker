package com.example.feedbacker.dto.response.profile;

public class ProfileResponse {

    private Long userId;
    private String displayName;
    private String avatarUrl;
    private String bio;

    public ProfileResponse(Long userId, String displayName, String avatarUrl, String bio) {
        this.userId = userId;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBio() {
        return bio;
    }

}
