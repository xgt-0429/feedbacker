package com.example.feedbacker.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CircleMerchant {
    private Long circleId;
    private Long merchantId;
    private LocalDateTime createdAt;
}
