package com.example.feedbacker.dto.response.post;

import java.util.Date;
import java.util.List;

public class PostDetailResponse {
    private Long id;
    private Long circleId;
    private Long merchantId;
    private String title;
    private String content;
    private Double score;
    private Date createdAt;
    private List<String> tags;
    private List<CommentResponse> comments;

    public PostDetailResponse(Long id, Long circleId, Long merchantId, String title, String content, Date createdAt, Double score) {
        this.id = id;
        this.circleId = circleId;
        this.merchantId = merchantId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}