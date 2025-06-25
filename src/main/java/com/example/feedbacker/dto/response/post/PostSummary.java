package com.example.feedbacker.dto.response.post;

import com.example.feedbacker.entity.Tag;

import java.util.Date;
import java.util.List;

public class PostSummary {
    private Long id;
    private Long authorId;
    private Long circleId;
    private Long merchantId;
    private String name;
    private String content;
    private Double score;
    private Date createdAt;
    private Date updatedAt;
    private String type;
    private String priceLevel;
    private List<String> images;
    private List<String> tags;

    public PostSummary(Long id, Long authorId, Long circleId, Long merchantId, String name, String content, Double score,
                       Date createdAt, Date updatedAt, String type, String priceLevel, List<String> images) {
        this.id = id;
        this.authorId = id;
        this.circleId = circleId;
        this.merchantId = merchantId;
        this.name = name;
        this.content = content;
        this.score = score;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
        this.priceLevel = priceLevel;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getCircleId() {
        return circleId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Double getScore() {
        return score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getType() {
        return type;
    }

    public String getPriceLevel() {
        return priceLevel;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}