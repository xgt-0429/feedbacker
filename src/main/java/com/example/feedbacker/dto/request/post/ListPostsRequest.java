package com.example.feedbacker.dto.request.post;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ListPostsRequest {

    private Long circleId;
    private Long merchantId;
    private Long authorId;
    private List<String> tags;

    @NotNull(message = "page 不能为空")
    @Min(value = 1, message = "page 最小为 1")
    private Integer page;

    @NotNull(message = "size 不能为空")
    @Min(value = 1, message = "size 最小为 1")
    private Integer size;
    private List<Long> circleIds;

    // getters & setters
    public Long getCircleId() { return circleId; }
    public void setCircleId(Long circleId) { this.circleId = circleId; }

    public Long getMerchantId() { return merchantId; }
    public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }

    public void setCircleIds(List<Long> circleIds) {
        this.circleIds = circleIds;
    }

    public List<Long> getCircleIds() {
        return circleIds;
    }
}