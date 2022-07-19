package com.job.service.impl;

import com.job.modal.JobEntity;
import com.job.payloads.JobDTO;
import com.job.payloads.PageDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.JobResponse;
import com.job.repository.JobRepo;
import com.job.repository.UserRepo;
import com.job.security.UserPrincipal;
import com.job.service.JobService;
import com.job.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public ApiResponse postJob(JobDTO jobDTO) {

        try {
            JobEntity job=new JobEntity();
            job.setJobTitle(jobDTO.getJobTitle());
            job.setJobDescription(jobDTO.getJobDescription());
            UserPrincipal user= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            job.setUser(userRepo.findById(user.getUserId()).get());
            jobRepo.save(job);
            return new ApiResponse(Boolean.TRUE,"job post successfully");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");

        }
    }

    @Override
    public ApiResponse deleteJob(long jobId) {
        try
        {

            JobEntity job = jobRepo.findById(jobId).get();
            UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            job.setUser(userRepo.findById(user.getUserId()).get());
            if (job.getUser().getUserId() == user.getUserId()) {
                jobRepo.deleteById(jobId);
                return new ApiResponse(Boolean.TRUE, "job delete successfully");
            } else {
                return new ApiResponse(Boolean.FALSE, "you have not permission to delete this job");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }

    }

    @Override
    public ApiResponse updateJob(long jobId,JobDTO jobDTO) {
        try
        {

            JobEntity job = jobRepo.findById(jobId).get();
            UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            job.setUser(userRepo.findById(user.getUserId()).get());
            if (job.getUser().getUserId() == user.getUserId()) {
                job.setJobTitle(jobDTO.getJobTitle());
                job.setJobDescription(jobDTO.getJobDescription());

                jobRepo.save(job);
                return new ApiResponse(Boolean.TRUE, "job update successfully");
            } else {
                return new ApiResponse(Boolean.FALSE, "you have not permission to update this job");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }
    }

    @Override
    public JobResponse getJobById(long jobId) {

        JobEntity job = jobRepo.findById(jobId).get();
        return new JobResponse(job.getJobId(),job.getJobTitle(),job.getJobDescription());
    }

    @Override
    public List<JobResponse> getMyJobs(PageDTO pageDTO) {

        try {

            Pageable pageable = PageRequest.of(pageDTO.getPageNo(), pageDTO.getPageSize(), Sort.Direction.DESC, AppConstants.JOB_ID);
            UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Page<JobEntity> jobs = jobRepo.findByUser(userRepo.findById(user.getUserId()).get(), pageable);

            List<JobEntity> joblist = new ArrayList<>(jobs.getContent());
            List<JobResponse> jobResponses = new ArrayList<>();
            for (JobEntity j : jobs) {
                JobResponse r = new JobResponse(j.getJobId(), j.getJobTitle(), j.getJobDescription());
                jobResponses.add(r);
            }
            return jobResponses;
            }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }

    }

    @Override
    public List<JobResponse> getAllJobs(PageDTO pageDTO) {
        try {

            Pageable pageable = PageRequest.of(pageDTO.getPageNo(), pageDTO.getPageSize(), Sort.Direction.DESC, AppConstants.JOB_ID);
            Page<JobEntity> jobs = jobRepo.findAll(pageable);

            List<JobEntity> joblist = new ArrayList<>(jobs.getContent());
            List<JobResponse> jobResponses = new ArrayList<>();
            for (JobEntity j : jobs) {
                JobResponse r = new JobResponse(j.getJobId(), j.getJobTitle(), j.getJobDescription());
                jobResponses.add(r);
            }
            return jobResponses;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
