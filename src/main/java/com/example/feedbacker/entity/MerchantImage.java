package com.example.feedbacker.entity;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 商家图片表
 */
@Setter
@Getter
public class MerchantImage {
    /**
    * 图片主键
    */
    @NotNull(message = "图片主键不能为null")
    private Long id;

    /**
    * 关联商家，merchants.id
    */
    @NotNull(message = "关联商家，merchants.id不能为null")
    private Long merchantId;

    /**
    * 图片 URL
    */
    @Size(max = 255,message = "图片 URL最大长度要小于 255")
    @NotBlank(message = "图片 URL不能为空")
    private String url;

    /**
    * 上传时间
    */
    @NotNull(message = "上传时间不能为null")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        sb.append(", id=").append(id);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", url=").append(url);
        sb.append(", createdAt=").append(createdAt);
        sb.append("]");
        return sb.toString();
    }
}