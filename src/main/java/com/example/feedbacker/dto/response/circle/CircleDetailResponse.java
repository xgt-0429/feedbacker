package com.example.feedbacker.dto.response.circle;

import com.example.feedbacker.dto.MemberDto;

import java.time.LocalDateTime;
import java.util.List;
public class CircleDetailResponse {

    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime createdAt;
    private List<MemberDto> members;

    public CircleDetailResponse(Long id,
                                String name,
                                String description,
                                Long ownerId,
                                LocalDateTime createdAt,
                                List<MemberDto> members) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.ownerId     = ownerId;
        this.createdAt   = createdAt;
        this.members     = members;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Long getOwnerId() { return ownerId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<MemberDto> getMembers() { return members; }

}
