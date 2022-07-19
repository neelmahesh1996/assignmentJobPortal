package com.job.service;

import com.job.payloads.JobDTO;
import com.job.payloads.PageDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.JobResponse;

import java.util.List;

public interface JobService {

    ApiResponse postJob(JobDTO jobDTO);
    ApiResponse deleteJob(long jobId);
    ApiResponse updateJob(long jobId,JobDTO jobDTO);
    JobResponse getJobById(long jobId);
    List<JobResponse> getMyJobs(PageDTO pageDTO);
    List<JobResponse> getAllJobs(PageDTO pageDTO);


}
