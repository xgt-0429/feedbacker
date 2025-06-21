package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class CircleDetailRequest {

    @NotNull(message = "circleId 不能为空")
    private Long circleId;

    public Long getCircleId() { return circleId; }
    public void setCircleId(Long circleId) { this.circleId = circleId; }

}
