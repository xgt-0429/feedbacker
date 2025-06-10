package com.example.feedbacker.entity;

import jakarta.validation.constraints.NotNull;

public class PostTags {
    @NotNull(message = "不能为null")
    private Long postId;

    @NotNull(message = "不能为null")
    private Long tagId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", tagId=").append(tagId);
        sb.append("]");
        return sb.toString();
    }
}