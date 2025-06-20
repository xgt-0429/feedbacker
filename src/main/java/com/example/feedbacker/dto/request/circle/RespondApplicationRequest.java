package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class RespondApplicationRequest {

    @NotNull private Long applicationId;
    @NotNull private Long circleId;
    @NotNull private Boolean accepted;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
