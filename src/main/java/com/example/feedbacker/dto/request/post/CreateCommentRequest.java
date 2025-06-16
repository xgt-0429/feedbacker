package com.example.feedbacker.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class CreateCommentRequest {

    @NotNull(message = "postId 不能为空")
    private Long postId;

    @NotBlank(message = "评论内容不能为空")
    private String content;

    private String userId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
