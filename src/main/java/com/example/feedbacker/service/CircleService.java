package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.circle.*;
import com.example.feedbacker.dto.response.circle.ApplicationResponse;
import com.example.feedbacker.dto.response.circle.CircleSummary;
import com.example.feedbacker.dto.response.circle.InvitationResponse;
import com.example.feedbacker.dto.response.circle.CircleDetailResponse;

import java.util.List;

public interface CircleService {

    Long createCircle(CreateCircleRequest req);

    void invite(InviteRequest req);

    List<InvitationResponse> listInvites(Long userId);

    void respondInvite(RespondInviteRequest req);

    void join(ApplyRequest req);

    List<ApplicationResponse> listApplications(Long circleId);

    void respondApplication(RespondApplicationRequest req);

    void removeMember(RemoveMemberRequest req);

    CircleDetailResponse getCircleDetail(Long circleId);

    void updateCircle(UpdateCircleRequest req);

    List<CircleSummary> listMyCircles();

}
