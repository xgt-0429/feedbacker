package com.example.feedbacker.dto.response.merchant;

import java.util.Date;

public class MerchantSummary {

    /**
     * 商家主键，自增
     */
    private Long id;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家描述
     */
    private String description;

    /**
     * 商家联系信息
     */
    private String contactInfo;

    /**
     * 记录创建时间
     */
    private Date createdAt;

    /**
     * 记录更新时间
     */
    private Date updatedAt;

    /**
     * 外部数据来源（例如 google_maps）
     */
    private String externalSource;

    /**
     * 外部系统中该商家的唯一 ID（例如 Place ID）
     */
    private String externalId;

    /**
     * 商家在地图上的地址
     */
    private String address;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 此商家记录由哪个用户创建（users.id）
     */
    private Long createdBy;

    public MerchantSummary(Long id, String name, String description, String contactInfo, Date createdAt, Date updatedAt, String externalSource, String externalId, String address, Double latitude, Double longitude, Long createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contactInfo = contactInfo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.externalSource = externalSource;
        this.externalId = externalId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getExternalSource() {
        return externalSource;
    }

    public void setExternalSource(String externalSource) {
        this.externalSource = externalSource;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
