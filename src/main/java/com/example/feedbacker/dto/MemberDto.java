package com.example.feedbacker.dto;

import java.time.LocalDateTime;

public class MemberDto {

    private Long id;
    private Long userId;
    private String role;
    private LocalDateTime joinedAt;

    public MemberDto(Long id, Long userId, String role, LocalDateTime joinedAt) {
        this.id       = id;
        this.userId   = userId;
        this.role     = role;
        this.joinedAt = joinedAt;
    }
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getRole() { return role; }
    public LocalDateTime getJoinedAt() { return joinedAt; }

}
