package com.example.feedbacker.dto.request.circle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCircleRequest {

    @NotNull(message = "circleId 不能为空")
    private Long circleId;

    @NotBlank(message = "name 不能为空")
    private String name;

    private String description;

    public Long getCircleId() { return circleId; }
    public void setCircleId(Long circleId) { this.circleId = circleId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
