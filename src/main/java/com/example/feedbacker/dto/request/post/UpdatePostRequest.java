package com.example.feedbacker.dto.request.post;

import jakarta.validation.constraints.*;

import java.util.List;

public class UpdatePostRequest {

    @NotNull(message = "postId 不能为空")
    private Long postId;

    @NotNull(message = "circleId 不能为空")
    private Long circleId;

    @NotBlank @Size(max = 255)
    private String title;

    @NotBlank
    private String content;

    @DecimalMin("1.0") @DecimalMax("5.0")
    private Double score;

    private List<@NotBlank String> tags;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


}
