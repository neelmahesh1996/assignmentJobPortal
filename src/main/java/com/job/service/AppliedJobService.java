package com.job.service;

import com.job.payloads.PageDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.AppliedJobResponse;

import java.util.List;

public interface AppliedJobService {
    ApiResponse applyJob(long jobId);
    List<AppliedJobResponse> myAppliedJob(PageDTO pageDTO);
    ApiResponse deleteApplication(long jobAppliedId);
}
