package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class RemoveMemberRequest {

    @NotNull private Long id;
    @NotNull private Long circleId;
    @NotNull private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
