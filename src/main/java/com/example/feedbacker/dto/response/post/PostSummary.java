package com.example.feedbacker.dto.response.post;

import java.util.Date;

public class PostSummary {
    private Long id;
    private Long circleId;
    private Long merchantId;
    private String title;
    private Double score;
    private Date createdAt;

    public PostSummary(Long id, Long circleId, Long merchantId, String title, Double score, Date createdAt) {
        this.id = id;
        this.circleId = circleId;
        this.merchantId = merchantId;
        this.title = title;
        this.score = score;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCircleId() {
        return circleId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getTitle() {
        return title;
    }

    public Double getScore() {
        return score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}