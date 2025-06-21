package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class RemoveMemberRequest {

    @NotNull private Long circleId;
    @NotNull private Long memberId;

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
