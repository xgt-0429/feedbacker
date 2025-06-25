package com.example.feedbacker.controller;


import com.example.feedbacker.dto.request.circle.*;
import com.example.feedbacker.dto.response.circle.ApplicationResponse;
import com.example.feedbacker.dto.response.circle.CircleDetailResponse;
import com.example.feedbacker.dto.response.circle.InvitationResponse;
import com.example.feedbacker.service.CircleService;
import com.example.feedbacker.dto.response.post.PostSummary;
import com.example.feedbacker.dto.response.ApiResponse;
import com.example.feedbacker.utils.CurrentUserUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/circles")
public class CircleController {

    private final CircleService circleService;

    public CircleController(CircleService circleService){
        this.circleService = circleService;
    }

    /** 创建朋友圈 */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> create(
            @Valid @RequestBody CreateCircleRequest req) {
        return ResponseEntity.ok(ApiResponse.success(circleService.createCircle(req)));
    }

    /**
     * 查看朋友圈
     * @param req
     * @return
     */
    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<CircleDetailResponse>> detail(
            @Valid @RequestBody CircleDetailRequest req) {
        CircleDetailResponse resp = circleService.getCircleDetail(req.getCircleId());
        return ResponseEntity.ok(ApiResponse.success(resp));
    }

    /** 编辑朋友圈详情（仅圈主可调用） */
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateCircle(
            @Valid @RequestBody UpdateCircleRequest req) {
        circleService.updateCircle(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 邀请用户加入 */
    @PostMapping("/invite")
    public ResponseEntity<ApiResponse<Void>> invite(
            @Valid @RequestBody InviteRequest req) {
        circleService.invite(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 用户查看邀请列表 */
    @GetMapping("/invitations/list")
    public ResponseEntity<ApiResponse<List<InvitationResponse>>> invites(){
        Long uid = CurrentUserUtil.getUserId();
        return ResponseEntity.ok(ApiResponse.success(circleService.listInvites(uid)));
    }

    /** 用户处理邀请请求 */
    @PostMapping("/invitations/process")
    public ResponseEntity<ApiResponse<Void>> respondInvite(
            @Valid @RequestBody RespondInviteRequest req) {
        circleService.respondInvite(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 申请加入 */
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(
            @Valid @RequestBody ApplyRequest req) {
        circleService.join(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 管理员查看加入请求 */
    @PostMapping("/applications/list")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> applications(
            @PathVariable Long circleId) {
        return ResponseEntity.ok(ApiResponse.success(circleService.listApplications(circleId)));
    }

    /** 管理员处理加入请求 */
    @PostMapping("/application/process")
    public ResponseEntity<ApiResponse<Void>> respondApplication(
            @Valid @RequestBody RespondApplicationRequest req) {
        circleService.respondApplication(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 踢出成员 */
    @PostMapping("/remove")
    public ResponseEntity<ApiResponse<Void>> removeMember(
            @Valid @RequestBody RemoveMemberRequest req) {
        circleService.removeMember(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}