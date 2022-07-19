package com.job.payloads.response;

import lombok.Data;

@Data
public class AppliedJobResponse {
    private long appliedJobId;
    private String jobTitle;
    private String JobDescription;

    public AppliedJobResponse(long appliedJobId, String jobTitle, String jobDescription) {
        this.appliedJobId = appliedJobId;
        this.jobTitle = jobTitle;
        JobDescription = jobDescription;
    }
}
