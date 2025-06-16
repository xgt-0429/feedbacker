package com.example.feedbacker.dto.request.post;

import jakarta.validation.constraints.*;

import java.util.List;

public class CreatePostRequest {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String source;
    private String externalId;

    @NotNull(message = "circleId 不能为空")
    private Long circleId;

    @NotBlank(message = "标题不能为空")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @DecimalMin(value = "1.0")
    @DecimalMax(value = "5.0")
    private Double score;

    private List<@NotBlank String> tags;

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
