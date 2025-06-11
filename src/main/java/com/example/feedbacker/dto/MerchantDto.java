package com.example.feedbacker.dto;

import java.util.Date;

public class MerchantDto {

    private Long id;
    private String name;
    private String description;
    private String contactInfo;
    private Date createdAt;
    private Date updatedAt;

    public MerchantDto(Long id, String name, String desc,
                       String contactInfo, Date createdAt, Date updatedAt) {
        this.id = id; this.name = name; this.description = desc;
        this.contactInfo = contactInfo;
        this.createdAt = createdAt; this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
