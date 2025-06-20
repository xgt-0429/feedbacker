package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class InviteRequest {

    @NotNull private Long circleId;
    @NotNull private Long inviteeId;

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

}
