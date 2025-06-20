package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.circle.*;
import com.example.feedbacker.dto.response.circle.ApplicationResponse;
import com.example.feedbacker.dto.response.circle.InvitationResponse;
import com.example.feedbacker.dto.response.post.PostSummary;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CircleService {

    Long createCircle(CreateCircleRequest req);

    void invite(InviteRequest req);

    List<InvitationResponse> listInvites(Long userId);

    void respondInvite(RespondInviteRequest req);

    void apply(ApplyRequest req);

    List<ApplicationResponse> listApplications(Long circleId);

    void respondApplication(RespondApplicationRequest req);

    void removeMember(RemoveMemberRequest req);

    List<PostSummary> listPostsInCircle(ListCirclePostsRequest req);

    List<PostSummary> listPostsInAllCircles();

}
