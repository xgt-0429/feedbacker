package com.example.feedbacker.dto.response.circle;

import java.util.Date;

public class ApplicationResponse {

    public Long applicationId;
    public Long applicantId;
    public Date appliedAt;
    public ApplicationResponse(Long id,Long a, Date at) {
        this.applicationId=id; this.applicantId=a; this.appliedAt=at;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }
}
