package com.example.feedbacker.dto.response.post;

import com.example.feedbacker.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostSummaryAssembler {

    /**
     * 把单个 Post 转为 PostSummary
     */
    public PostSummary toSummary(Post p) {
        return new PostSummary(
                p.getId(),
                p.getCircleId(),
                p.getMerchantId(),
                p.getTitle(),
                p.getScore(),
                p.getCreatedAt()
        );
    }

}
