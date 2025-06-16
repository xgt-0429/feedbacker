package com.example.feedbacker.dto.request.merchant;

import jakarta.validation.constraints.NotNull;
public class PostDetailRequest {

    @NotNull public Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
