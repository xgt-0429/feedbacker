package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotNull;
public class RespondInviteRequest {

    @NotNull private Long invitationId;
    @NotNull private Boolean accepted;

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
