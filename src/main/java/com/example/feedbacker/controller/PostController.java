package com.example.feedbacker.controller;

import com.example.feedbacker.dto.request.post.PostDetailRequest;
import com.example.feedbacker.dto.request.post.*;
import com.example.feedbacker.dto.response.post.PostDetailResponse;
import com.example.feedbacker.dto.response.post.PostSummary;
import com.example.feedbacker.service.PostService;
import com.example.feedbacker.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService svc;
    public PostController(PostService svc){ this.svc = svc; }

    /** 创建帖子（必须指定 circleId） */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> create(
            @Valid @RequestBody CreatePostRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.create(req)));
    }

    /** 按圈子查看帖子列表 */
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<List<PostSummary>>> listByCircle(
            @Valid @RequestBody ListPostsRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.listByCircle(req)));
    }

    /** 查看帖子列表 */
    @PostMapping("/listAll")
    public ResponseEntity<ApiResponse<List<PostSummary>>> listPostsInAllCircles(
            @Valid @RequestBody ListPostsRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.listPostsInAllCircles(req)));
    }

    /** 查看帖子详情 */
    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<PostDetailResponse>> detail(
            @Valid @RequestBody PostDetailRequest req) {
        return ResponseEntity.ok(ApiResponse.success(svc.detail(req)));
    }

    /** 编辑帖子(可修改圈子、标签等) */
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Void>> update(
            @Valid @RequestBody UpdatePostRequest req) {
        svc.update(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 创建评论 */
    @PostMapping("/comment")
    public ResponseEntity<ApiResponse<Void>> comment(
            @Valid @RequestBody CreateCommentRequest req) {
        svc.comment(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** 删除评论 */
    @PostMapping("/comment/delete")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @Valid @RequestBody DeleteCommentRequest req) {
        svc.deleteComment(req);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}