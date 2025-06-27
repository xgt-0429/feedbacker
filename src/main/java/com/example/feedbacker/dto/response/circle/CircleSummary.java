package com.example.feedbacker.dto.response.circle;

import java.time.LocalDateTime;

public class CircleSummary {

    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime createdAt;

    public CircleSummary(Long id,
                         String name,
                         String description,
                         Long ownerId,
                         LocalDateTime createdAt) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.ownerId     = ownerId;
        this.createdAt   = createdAt;
    }

    // ---- getters and setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

}
