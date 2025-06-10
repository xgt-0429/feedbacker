package com.example.feedbacker.entity;

import java.util.Date;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CircleMembers {
    @NotNull(message = "不能为null")
    private Long id;

    @NotNull(message = "不能为null")
    private Long circleId;

    @NotNull(message = "不能为null")
    private Long userId;

    @NotNull(message = "不能为null")
    private Object role;

    private Date joinedAt;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", circleId=").append(circleId);
        sb.append(", userId=").append(userId);
        sb.append(", role=").append(role);
        sb.append(", joinedAt=").append(joinedAt);
        sb.append("]");
        return sb.toString();
    }
}