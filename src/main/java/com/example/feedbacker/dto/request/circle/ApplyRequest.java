package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class ApplyRequest {

    @NotNull private Long circleId;

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

}
