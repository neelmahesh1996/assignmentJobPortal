package com.job.controller;

import com.job.payloads.JobDTO;
import com.job.payloads.PageDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.JobResponse;
import com.job.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobServiceImpl jobService;
    @PostMapping("/post")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse> addJob(@Valid @RequestBody JobDTO jobDTO, BindingResult result)
    {
        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(new ApiResponse(Boolean.FALSE,result.getAllErrors().get(0).getDefaultMessage()));
        }
        //do post job
        return ResponseEntity.ok(jobService.postJob(jobDTO));
    }

    @GetMapping("/myjobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public List<JobResponse> getMyJob(@Valid @RequestBody PageDTO pageDTO)
    {
        //return all recruiter jobs
        return jobService.getMyJobs(pageDTO);
    }

    @GetMapping("/myjobs/{jobId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public JobResponse getJobById(@Valid @PathVariable("jobId") long jobId)
    {
        //return recruiter jobs with id
        return jobService.getJobById(jobId);
    }

    @GetMapping("/alljobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<JobResponse> getAllJob(@Valid @RequestBody PageDTO pageDTO)
    {
        //return all available jobs for candidate
        return jobService.getAllJobs(pageDTO);
    }

    @PatchMapping("/edit/{jobId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse> updateJob(@Valid @PathVariable("jobId") long jobId, @RequestBody JobDTO jobDTO, BindingResult result)
    {
        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(new ApiResponse(Boolean.FALSE,result.getAllErrors().get(0).getDefaultMessage()));
        }
        //do update job
        return ResponseEntity.ok().body(jobService.updateJob(jobId,jobDTO));
    }

    @DeleteMapping("/delete/{jobId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse> deleteJob(@PathVariable("jobId") long jobId)
    {
        //delete job by id
        return ResponseEntity.ok().body(jobService.deleteJob(jobId));
    }
}
