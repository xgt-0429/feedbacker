package com.example.feedbacker.service.impl;

import com.example.feedbacker.dto.MemberDto;
import com.example.feedbacker.dto.request.post.ListPostsRequest;
import com.example.feedbacker.dto.response.circle.CircleDetailResponse;
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


    public CircleServiceImpl(CircleMapper circleMapper,
                             CircleMemberMapper memberMapper,
                             CircleInvitationMapper invMapper,
                             CircleApplicationMapper appMapper
                            ) {
        this.circleMapper = circleMapper;
        this.memberMapper = memberMapper;
        this.invMapper    = invMapper;
        this.appMapper    = appMapper;

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
    public void updateCircle(UpdateCircleRequest req) {
        Long uid = CurrentUserUtil.getUserId();
        Circle existing = circleMapper.findById(req.getCircleId());
        if (existing == null) {
            throw new ApiException("圈子不存在");
        }
        if (!existing.getOwnerId().equals(uid)) {
            throw new ApiException("只有圈主才能编辑");
        }
        Circle toUpdate = new Circle();
        toUpdate.setId(req.getCircleId());
        toUpdate.setName(req.getName());
        toUpdate.setDescription(req.getDescription());
        int rows = circleMapper.updateByPrimaryKeySelective(toUpdate);
        if (rows == 0) {
            throw new ApiException("更新失败，可能无权限或数据未变化");
        }
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
                .map(inv -> new InvitationResponse(inv.getId(), inv.getCircleId(), inv.getInviterId(), inv.getCreatedAt()))
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
    public void join(ApplyRequest req) {
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
                .map(app-> new ApplicationResponse(app.getId(), app.getUserId(), app.getAppliedAt()))
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
        memberMapper.delete(req.getCircleId(), req.getMemberId());
    }

    @Override
    public CircleDetailResponse getCircleDetail(Long circleId) {
        Circle c = circleMapper.findById(circleId);
        if (c == null) {
            throw new ApiException("圈子不存在");
        }
        List<CircleMember> members = memberMapper.findByCircleId(circleId);
        List<MemberDto> dtos = members.stream()
                .map(m -> new MemberDto(
                        m.getId(),
                        m.getUserId(),
                        m.getRole(),
                        m.getJoinedAt()
                ))
                .collect(Collectors.toList());

        return new CircleDetailResponse(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.getOwnerId(),
                c.getCreatedAt(),
                dtos
        );
    }

}
