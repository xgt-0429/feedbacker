package com.example.feedbacker.dto.response.circle;

public class ApplicationResponse {

    public Long applicationId;
    public Long applicantId;
    public ApplicationResponse(Long id,Long a){
        this.applicationId=id; this.applicantId=a;
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

}
