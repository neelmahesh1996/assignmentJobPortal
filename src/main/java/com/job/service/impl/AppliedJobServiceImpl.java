package com.job.service.impl;

import com.job.modal.AppliedJobEntity;
import com.job.modal.JobEntity;
import com.job.modal.UserEntity;
import com.job.payloads.PageDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.AppliedJobResponse;
import com.job.repository.AppliedJobRepo;
import com.job.repository.JobRepo;
import com.job.repository.UserRepo;
import com.job.security.UserPrincipal;
import com.job.service.AppliedJobService;
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
public class AppliedJobServiceImpl implements AppliedJobService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private AppliedJobRepo appliedJobRepo;
    @Override
    public ApiResponse applyJob(long jobId) {
        try
        {
            if(jobId>0 && jobRepo.existsById(jobId)) {
                JobEntity job = jobRepo.findById(jobId).get();
                UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserEntity user = userRepo.findById(userPrincipal.getUserId()).get();
                AppliedJobEntity jobEntity = new AppliedJobEntity();
                jobEntity.setUser(user);
                jobEntity.setJob(job);
                appliedJobRepo.save(jobEntity);
                //send email to recruiter and candidate
                return new ApiResponse(Boolean.TRUE,"Job applied successfully");
            }
            else
            {
                return new ApiResponse(Boolean.FALSE,"Job Id is not valid");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }

    }

    @Override
    public List<AppliedJobResponse> myAppliedJob(PageDTO pageDTO) {
        try
        {
            Pageable pageable = PageRequest.of(pageDTO.getPageNo(), pageDTO.getPageSize(), Sort.Direction.DESC, AppConstants.JOB_ID);
            UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Page<AppliedJobEntity> jobs=appliedJobRepo.findByUser(userRepo.findById(user.getUserId()).get(),pageable);
            System.out.println("ok");
            List<AppliedJobEntity> appliedJob=jobs.getContent();
            System.out.println("ok");
            List<AppliedJobResponse> appliedJobResponses=new ArrayList<>();
            System.out.println("ok");
            for(AppliedJobEntity appliedJobEntity:appliedJob)
            {

                AppliedJobResponse aJob=new AppliedJobResponse(appliedJobEntity.getAppliedJobId(),appliedJobEntity.getJob().getJobTitle(),appliedJobEntity.getJob().getJobDescription());
                appliedJobResponses.add(aJob);
            }
            return appliedJobResponses;

        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public ApiResponse deleteApplication(long jobAppliedId) {
        try {
            if (jobAppliedId > 0 && appliedJobRepo.existsById(jobAppliedId)) {
                appliedJobRepo.deleteById(jobAppliedId);
                return new ApiResponse(Boolean.TRUE, "application successfully delete");
            }
            else
            {
                return new ApiResponse(Boolean.TRUE, "application id not valid");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }
    }
}
