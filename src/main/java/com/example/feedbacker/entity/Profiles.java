package com.example.feedbacker.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Profiles {
    @NotNull(message = "不能为null")
    private Long id;

    private Long userId;

    @Size(max = 50,message = "最大长度要小于 50")
    private String displayName;

    @Size(max = 255,message = "最大长度要小于 255")
    private String avatarUrl;

    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", displayName=").append(displayName);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", bio=").append(bio);
        sb.append("]");
        return sb.toString();
    }
}