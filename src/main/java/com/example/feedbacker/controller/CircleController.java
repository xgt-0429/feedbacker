package com.example.feedbacker.controller;


import com.example.feedbacker.dto.request.circle.*;
import com.example.feedbacker.dto.response.circle.ApplicationResponse;
import com.example.feedbacker.dto.response.circle.InvitationResponse;
import com.example.feedbacker.dto.response.post.PostSummary;
import com.example.feedbacker.service.CircleService;
import com.example.feedbacker.dto.response.ApiResponse;
import com.example.feedbacker.utils.CurrentUserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/circles")
public class CircleController {

    private final CircleService svc;
    public CircleController(CircleService svc){
        this.svc = svc;
    }

    /** 创建朋友圈 */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> create(
            @Valid @RequestBody CreateCircleRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.createCircle(req)));
    }

    /** 邀请用户加入 */
    @PostMapping("/invite")
    public ResponseEntity<ApiResponse<Void>> invite(
            @Valid @RequestBody InviteRequest req) {
        svc.invite(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 查看收到的邀请 */
    @GetMapping("/invites")
    public ResponseEntity<ApiResponse<List<InvitationResponse>>> invites(){
        Long uid = CurrentUserUtil.getUserId();
        return ResponseEntity.ok(ApiResponse.success(svc.listInvites(uid)));
    }

    /** 处理邀请 */
    @PostMapping("/invite/respond")
    public ResponseEntity<ApiResponse<Void>> respondInvite(
            @Valid @RequestBody RespondInviteRequest req) {
        svc.respondInvite(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 申请加入 */
    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<Void>> apply(
            @Valid @RequestBody ApplyRequest req) {
        svc.apply(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 管理员查看申请 */
    @GetMapping("/{circleId}/applications")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> applications(
            @PathVariable Long circleId) {
        return ResponseEntity.ok(ApiResponse.success(svc.listApplications(circleId)));
    }

    /** 处理申请 */
    @PostMapping("/apply/respond")
    public ResponseEntity<ApiResponse<Void>> respondApplication(
            @Valid @RequestBody RespondApplicationRequest req) {
        svc.respondApplication(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 踢出成员 */
    @PostMapping("/remove")
    public ResponseEntity<ApiResponse<Void>> removeMember(
            @Valid @RequestBody RemoveMemberRequest req) {
        svc.removeMember(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 查看单个圈子内的帖子 */
    @PostMapping("/posts")
    public ResponseEntity<ApiResponse<List<PostSummary>>> postsInCircle(
            @Valid @RequestBody ListCirclePostsRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.listPostsInCircle(req)));
    }

    /** 查看所有加入的圈子的帖子 */
    @GetMapping("/posts/all")
    public ResponseEntity<ApiResponse<List<PostSummary>>> postsInAllCircles(){
        return ResponseEntity.ok(ApiResponse.success(svc.listPostsInAllCircles()));
    }
}