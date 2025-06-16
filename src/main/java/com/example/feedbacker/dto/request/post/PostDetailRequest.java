package com.example.feedbacker.dto.request.post;

import jakarta.validation.constraints.NotNull;
public class PostDetailRequest {

    @NotNull(message = "postId 不能为空")
    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
