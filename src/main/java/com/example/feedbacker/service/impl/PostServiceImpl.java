package com.example.feedbacker.service.impl;


import com.example.feedbacker.dto.request.post.PostDetailRequest;
import com.example.feedbacker.dto.request.post.*;
import com.example.feedbacker.dto.response.post.CommentResponse;
import com.example.feedbacker.dto.response.post.PostDetailResponse;
import com.example.feedbacker.dto.response.post.PostSummary;
import com.example.feedbacker.dto.response.post.PostSummaryAssembler;
import com.example.feedbacker.entity.*;
import com.example.feedbacker.exception.ApiException;
import com.example.feedbacker.mapper.*;
import com.example.feedbacker.service.PostService;
import com.example.feedbacker.utils.CurrentUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final TagMapper tagMapper;
    private final PostTagMapper postTagMapper;
    private final CommentMapper commentMapper;
    private final MerchantMapper merchantMapper;
    private final CircleMerchantMapper circleMerchantMapper;
    private final CircleMemberMapper memberMapper;
    private final PostSummaryAssembler postAsm;
    private final com.example.feedbacker.service.MerchantService merchantService;
    private final PostImageMapper postImageMapper;

    public PostServiceImpl(PostMapper pm, TagMapper tm,
                           PostTagMapper ptm, CommentMapper cm, MerchantMapper mmp, CircleMerchantMapper cmm, CircleMemberMapper memberMapper, PostSummaryAssembler postAsm,
                           com.example.feedbacker.service.MerchantService ms, PostImageMapper postImageMapper) {
        this.postMapper      = pm;
        this.tagMapper       = tm;
        this.postTagMapper   = ptm;
        this.commentMapper   = cm;
        this.memberMapper = memberMapper;
        this.postAsm = postAsm;
        this.merchantService = ms;
        this.merchantMapper = mmp;
        this.circleMerchantMapper = cmm;
        this.postImageMapper = postImageMapper;
    }

    @Override
    @Transactional
    public Long create(CreatePostRequest req) {
        Long userId = CurrentUserUtil.getUserId();

        // 1. 先根据 externalId 查商家
        Merchant merchant = merchantMapper.findBySourceExternal(
                req.getExternalId()
        );

        // 2. 如果没查到，就新建一条
        if (merchant == null) {
            merchant = new Merchant();
            merchant.setName(req.getName());
            merchant.setAddress(req.getAddress());
            merchant.setLatitude(req.getLatitude());
            merchant.setLongitude(req.getLongitude());
            merchant.setExternalId(req.getExternalId());
            merchant.setCreatedBy(userId);
            merchantMapper.insert(merchant);
            //圈子和商家做关联
            CircleMerchant circleMerchant = new CircleMerchant();
            circleMerchant.setMerchantId(merchant.getId());
            circleMerchant.setCircleId(req.getCircleId());
            circleMerchantMapper.insert(circleMerchant);
        }
        Long merchantId = merchant.getId();

        // 3. 插入帖子
        Post post = new Post();
        post.setAuthorId(userId);
        post.setCircleId(req.getCircleId());
        post.setMerchantId(merchantId);
        post.setName(req.getName());
        post.setContent(req.getContent());
        post.setScore(req.getScore());
        post.setType(req.getType());
        post.setPriceLevel(req.getPriceLevel());
        postMapper.insert(post);

        // 处理标签
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

        // 处理图片
        if (req.getImages() != null) {
            for (String url : req.getImages()) {
                PostImage postImage = new PostImage();
                postImage.setUrl(url);
                postImage.setPostId(post.getId());
                postImageMapper.insert(postImage);
            }
        }
        return post.getId();
    }

    @Override
    public List<PostSummary> listByCircle(ListPostsRequest req) {
        List<Post> posts = postMapper.selectPostList(req);
        List<PostSummary> summaries = new ArrayList<>();

        for (Post p : posts) {
            // 1. 基本字段映射
            PostSummary summary = new PostSummary(
                    p.getId(), p.getAuthorId(), p.getCircleId(), p.getMerchantId(),
                    p.getName(), p.getContent(), p.getScore(),
                    p.getCreatedAt(), p.getUpdatedAt(),
                    p.getType(), p.getPriceLevel(), p.getImages()
            );

            // 2. 查询图片 URL 列表
            List<String> images = postImageMapper.findUrlsByPostId(p.getId());
            summary.setImages(images);
            List<String> tagNames = postTagMapper.findNamesByPostId(p.getId());
            summary.setTags(tagNames);

            summaries.add(summary);
        }

        return summaries;
    }

    /**
     * 查看所有朋友圈内的帖子
     * @return
     */
    @Override
    public List<PostSummary> listPostsInAllCircles(ListPostsRequest req) {
        // 1) 填充 circleIds
        Long uid = CurrentUserUtil.getUserId();
        req.setCircleIds(memberMapper.findCircleIdsByUser(uid));

        // 2) 直接传 req 给 Mapper
        List<Post> posts = postMapper.selectPostList(req);

        // 3) 传统 for-loop 转 DTO 并去重
        List<PostSummary> summaries = new ArrayList<>();
        Set<Long> seen = new HashSet<>();
        for (Post p : posts) {
            if (seen.add(p.getId())) {
                PostSummary s = postAsm.toSummary(p);
                s.setImages(postImageMapper.findUrlsByPostId(p.getId()));
                List<String> tagNames = postTagMapper.findNamesByPostId(p.getId());
                s.setTags(tagNames);
                summaries.add(s);
            }
        }
        return summaries;
    }


    @Override
    public PostDetailResponse detail(PostDetailRequest req) {
        Post p = postMapper.selectByPrimaryKey(req.getPostId());
        if (p == null) throw new ApiException("帖子不存在");
        PostDetailResponse detail = new PostDetailResponse(
                p.getId(), p.getCircleId(), p.getMerchantId(),
                p.getName(), p.getContent(),
                p.getCreatedAt(), p.getScore()
        );
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
        p.setName(req.getTitle());
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