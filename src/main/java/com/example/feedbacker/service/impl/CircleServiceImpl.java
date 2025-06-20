package com.example.feedbacker.service.impl;

import com.example.feedbacker.dto.request.circle.*;
import com.example.feedbacker.dto.response.circle.ApplicationResponse;
import com.example.feedbacker.dto.response.circle.InvitationResponse;
import com.example.feedbacker.dto.response.post.PostSummary;
import com.example.feedbacker.dto.response.post.PostSummaryAssembler;
import com.example.feedbacker.entity.*;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.*;
import com.example.feedbacker.service.CircleService;
import com.example.feedbacker.utils.CurrentUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CircleServiceImpl implements CircleService {

    private final CircleMapper circleMapper;
    private final CircleMemberMapper memberMapper;
    private final CircleInvitationMapper invMapper;
    private final CircleApplicationMapper appMapper;
    private final PostMapper postMapper;
    private final PostSummaryAssembler postAsm;

    public CircleServiceImpl(CircleMapper circleMapper,
                             CircleMemberMapper memberMapper,
                             CircleInvitationMapper invMapper,
                             CircleApplicationMapper appMapper,
                             PostMapper postMapper,
                             PostSummaryAssembler postAsm) {
        this.circleMapper = circleMapper;
        this.memberMapper = memberMapper;
        this.invMapper    = invMapper;
        this.appMapper    = appMapper;
        this.postMapper   = postMapper;
        this.postAsm      = postAsm;
    }

    @Override @Transactional
    public Long createCircle(CreateCircleRequest req) {
        Long uid = CurrentUserUtil.getUserId();
        Circle c = new Circle();
        c.setName(req.getName());
        c.setDescription(req.getDescription());
        c.setOwnerId(uid);
        circleMapper.insert(c);
        // 创建者自动入圈
        CircleMember member = new CircleMember();
        member.setUserId(uid);
        member.setCircleId(c.getId());
        member.setRole("Admin");
        memberMapper.insert(member);
        return c.getId();
    }

    @Override
    public void invite(InviteRequest req) {
        Long inviter = CurrentUserUtil.getUserId();
        if (memberMapper.exists(req.getCircleId(), inviter)==0) {
            throw new ApiException("你不是圈子成员");
        }
        CircleInvitation invitation = new CircleInvitation();
        invitation.setCircleId(req.getCircleId());
        invitation.setInviterId(inviter);
        invitation.setInviteeId(req.getInviteeId());
        invitation.setStatus("Pending");
        invMapper.insert(invitation);
    }

    @Override
    public List<InvitationResponse> listInvites(Long userId) {
        return invMapper.findPendingByInvitee(userId).stream()
                .map(inv -> new InvitationResponse(inv.getId(), inv.getCircleId(), inv.getInviterId()))
                .collect(Collectors.toList());
    }

    /**
     * 用户处理邀请
     * @param req
     */
    @Override @Transactional
    public void respondInvite(RespondInviteRequest req) {
        invMapper.updateStatus(req.getInvitationId(), req.getAccepted()?"ACCEPTED":"REJECTED");
        if (req.getAccepted()) {
            var inv = invMapper.findPendingByInvitee(CurrentUserUtil.getUserId())
                    .stream().filter(i->i.getId().equals(req.getInvitationId()))
                    .findFirst().orElseThrow();
            CircleMember member = new CircleMember();
            member.setRole("User");
            member.setUserId(CurrentUserUtil.getUserId());
            member.setCircleId(inv.getCircleId());
            memberMapper.insert(member);
        }
    }

    /**
     * 申请加入朋友圈
     * @param req
     */
    @Override
    public void apply(ApplyRequest req) {
        Long uid = CurrentUserUtil.getUserId();
        CircleApplication circleApplication = new CircleApplication();
        circleApplication.setCircleId(req.getCircleId());
        circleApplication.setUserId(uid);
        circleApplication.setStatus("Pending");
        appMapper.insert(circleApplication);
    }

    /**
     * 查看申请列表
     * @param circleId
     * @return
     */
    @Override
    public List<ApplicationResponse> listApplications(Long circleId) {
        return appMapper.findPendingByCircle(circleId).stream()
                .map(app-> new ApplicationResponse(app.getId(), app.getUserId()))
                .collect(Collectors.toList());
    }

    /**
     * 管理员处理申请
     * @param req
     */
    @Override @Transactional
    public void respondApplication(RespondApplicationRequest req) {
        appMapper.updateStatus(req.getApplicationId(), req.getAccepted()?"ACCEPTED":"REJECTED");
        if (req.getAccepted()) {
            CircleMember member = new CircleMember();
            member.setRole("User");
            member.setUserId(CurrentUserUtil.getUserId());
            member.setCircleId(req.getCircleId());
            memberMapper.insert(member);
        }
    }

    /**
     * 移除朋友圈成员
     * @param req
     */
    @Override
    public void removeMember(RemoveMemberRequest req) {
        Long uid = CurrentUserUtil.getUserId();
        Circle c = circleMapper.findById(req.getCircleId());
        if (!c.getOwnerId().equals(uid)) {
            throw new ApiException("仅圈主可踢人");
        }
        memberMapper.deleteByPrimaryKey(req.getId());
    }

    /**
     * 查看当前朋友圈帖子
     * @param req
     * @return
     */
    @Override
    public List<PostSummary> listPostsInCircle(ListCirclePostsRequest req) {
        // check membership
        Long uid = CurrentUserUtil.getUserId();
        if (memberMapper.exists(req.getCircleId(), uid)==0) {
            throw new ApiException("你不在圈内");
        }
        return postMapper.findByCircle(req.getCircleId()).stream()
                .map(postAsm::toSummary).toList();
    }

    /**
     * 查看所有朋友圈内的帖子
     * @return
     */
    @Override
    public List<PostSummary> listPostsInAllCircles() {
        Long uid = CurrentUserUtil.getUserId();
        List<Long> cids = memberMapper.findCircleIdsByUser(uid);
        return cids.stream()
                .flatMap(cid-> postMapper.findByCircle(cid).stream())
                .map(postAsm::toSummary)
                .collect(Collectors.toList());
    }

}
