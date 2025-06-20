package com.example.feedbacker.dto.response.circle;

public class InvitationResponse {

    public Long invitationId;
    public Long circleId;
    public Long inviterId;
    public InvitationResponse(Long id,Long c,Long i){
        this.invitationId=id; this.circleId=c; this.inviterId=i;
    }

}
