package com.example.feedbacker.entity;

import java.util.Date;
import jakarta.validation.constraints.NotNull;

public class CircleApplications {
    @NotNull(message = "不能为null")
    private Long id;

    @NotNull(message = "不能为null")
    private Long circleId;

    @NotNull(message = "不能为null")
    private Long userId;

    @NotNull(message = "不能为null")
    private Object status;

    private Date appliedAt;

    private Date processedAt;

    private Long processedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCircleId() {
        return circleId;
    }

    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }

    public Date getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Date processedAt) {
        this.processedAt = processedAt;
    }

    public Long getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(Long processedBy) {
        this.processedBy = processedBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", circleId=").append(circleId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", appliedAt=").append(appliedAt);
        sb.append(", processedAt=").append(processedAt);
        sb.append(", processedBy=").append(processedBy);
        sb.append("]");
        return sb.toString();
    }
}