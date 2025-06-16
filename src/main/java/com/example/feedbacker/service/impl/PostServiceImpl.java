package com.example.feedbacker.service.impl;


import com.example.feedbacker.dto.request.post.PostDetailRequest;
import com.example.feedbacker.dto.request.post.*;
import com.example.feedbacker.dto.response.post.CommentResponse;
import com.example.feedbacker.dto.response.post.PostDetailResponse;
import com.example.feedbacker.dto.response.post.PostSummary;
import com.example.feedbacker.entity.*;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.*;
import com.example.feedbacker.service.PostService;
import com.example.feedbacker.utils.CurrentUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final TagMapper tagMapper;
    private final PostTagMapper postTagMapper;
    private final CommentMapper commentMapper;
    private final MerchantMapper merchantMapper;

    private final com.example.feedbacker.service.MerchantService merchantService;

    public PostServiceImpl(PostMapper pm, TagMapper tm,
                           PostTagMapper ptm, CommentMapper cm, MerchantMapper mmp,
                           com.example.feedbacker.service.MerchantService ms) {
        this.postMapper      = pm;
        this.tagMapper       = tm;
        this.postTagMapper   = ptm;
        this.commentMapper   = cm;
        this.merchantService = ms;
        this.merchantMapper = mmp;
    }

    @Override
    @Transactional
    public Long create(CreatePostRequest req) {
        Long userId = CurrentUserUtil.getUserId();

        // 1. 先根据 externalSource + externalId 查商家
        Merchant merchant = merchantMapper.findBySourceExternal(
                req.getSource(), req.getExternalId()
        );

        // 2. 如果没查到，就新建一条
        if (merchant == null) {
            merchant = new Merchant();
            merchant.setName(req.getName());
            merchant.setAddress(req.getAddress());
            merchant.setLatitude(req.getLatitude());
            merchant.setLongitude(req.getLongitude());
            merchant.setExternalSource(req.getSource());
            merchant.setExternalId(req.getExternalId());
            merchant.setCreatedBy(userId);
            merchantMapper.insert(merchant);
        }
        Long merchantId = merchant.getId();

        // 3. 插入帖子
        Post post = new Post();
        post.setAuthorId(userId);
        post.setCircleId(req.getCircleId());
        post.setMerchantId(merchantId);
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        post.setScore(req.getScore());
        postMapper.insert(post);

        // 4. 处理标签（可选）
        if (req.getTags() != null) {
            for (String tagName : req.getTags()) {
                Tag tag = tagMapper.findByName(tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setName(tagName);
                    tagMapper.insert(tag);
                }
                postTagMapper.insert(new PostTag(post.getId(), tag.getId()));
            }
        }

        return post.getId();
    }

    @Override
    public List<PostSummary> listByCircle(ListPostsRequest req) {
        postMapper.findByCircle(req.getCircleId()).stream().map(p -> {
            return new PostSummary(
                    p.getId(), p.getCircleId(), p.getMerchantId(),
                    p.getTitle(), p.getScore(), p.getCreatedAt()
            );
        }).collect(Collectors.toList());

        return null;
    }

    @Override
    public PostDetailResponse detail(PostDetailRequest req) {
        Post p = postMapper.selectByPrimaryKey(req.getPostId());
        if (p == null) throw new ApiException("帖子不存在");
        PostDetailResponse detail = new PostDetailResponse(
                p.getId(), p.getCircleId(), p.getMerchantId(),
                p.getTitle(), p.getContent(),
                p.getCreatedAt(), p.getScore()
        );
        // 标签
        detail.setTags(postTagMapper.findByPostId(p.getId())
                .stream().map(Tag::getName).toList());
        // 评论
        detail.setComments(commentMapper.findByPostId(p.getId())
                .stream().map(c -> new CommentResponse(
                        c.getId(), c.getAuthorId(), c.getContent(), c.getCreatedAt()
                )).toList());
        return detail;
    }

    @Override @Transactional
    public void update(UpdatePostRequest req) {
        Post p = new Post();
        p.setId(req.getPostId());
        p.setCircleId(req.getCircleId());
        p.setTitle(req.getTitle());
        p.setContent(req.getContent());
        p.setScore(req.getScore());
        if (postMapper.updateByPrimaryKey(p) == 0) {
            throw new ApiException("更新失败");
        }
        postTagMapper.delete(req.getPostId());
        if (req.getTags()!=null) {
            for (String name: req.getTags()) {
                Tag tag = tagMapper.findByName(name);
                if (tag==null) {
                    tag = new Tag(); tag.setName(name);
                    tagMapper.insert(tag);
                }
                postTagMapper.insert(new PostTag(req.getPostId(),tag.getId()));
            }
        }
    }

    @Override
    public void comment(CreateCommentRequest req) {
        Comment c = new Comment();
        c.setPostId(req.getPostId());
        c.setAuthorId(CurrentUserUtil.getUserId());
        c.setContent(req.getContent());
        commentMapper.insert(c);
    }

    @Override
    public void deleteComment(DeleteCommentRequest req) {
        commentMapper.deleteByPrimaryKey(req.getCommentId());
    }
}