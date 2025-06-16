package com.example.feedbacker.dto.request.post;

import jakarta.validation.constraints.NotNull;
public class DeleteCommentRequest {

    @NotNull(message = "commentId 不能为空")
    private Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
