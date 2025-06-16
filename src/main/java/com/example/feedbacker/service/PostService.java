package com.example.feedbacker.service;

import com.example.feedbacker.dto.request.post.*;
import com.example.feedbacker.dto.response.post.PostDetailResponse;
import com.example.feedbacker.dto.response.post.PostSummary;

import java.util.List;

public interface PostService {

    Long create(CreatePostRequest req);
    List<PostSummary> listByCircle(ListPostsRequest req);
    PostDetailResponse detail(PostDetailRequest req);
    void update(UpdatePostRequest req);
    void comment(CreateCommentRequest req);
    void deleteComment(DeleteCommentRequest req);

}
