package com.example.feedbacker.entity;

import java.util.Date;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户收藏商家表
 */
@Setter
@Getter
public class MerchantFavorites {
    /**
    * 收藏者，对应 users.id
    */
    @NotNull(message = "收藏者，对应 users.id不能为null")
    private Long userId;

    /**
    * 被收藏商家，对应 merchants.id
    */
    @NotNull(message = "被收藏商家，对应 merchants.id不能为null")
    private Long merchantId;

    /**
    * 收藏时间
    */
    @NotNull(message = "收藏时间不能为null")
    private Date createdAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}