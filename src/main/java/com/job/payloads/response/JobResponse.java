package com.job.payloads.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class JobResponse {

    private long jobId;
    private String jobTitle;
    private String jobDescription;

    public JobResponse(long jobId, String jobTitle, String jobDescription) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

}
