package com.example.feedbacker.dto.response.circle;

import java.time.LocalDateTime;

public class InvitationResponse {

    public Long invitationId;
    public Long circleId;
    public Long inviterId;
    public LocalDateTime createdAt;
    public InvitationResponse(Long id,Long c,Long i, LocalDateTime ca) {
        this.invitationId=id; this.circleId=c; this.inviterId=i; this.createdAt=ca;
    }

}
