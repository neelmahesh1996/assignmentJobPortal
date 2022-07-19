package com.job.controller;

import com.job.payloads.PageDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.AppliedJobResponse;
import com.job.service.impl.AppliedJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("jobapplication")
public class AppliedJobController {
    @Autowired
    private AppliedJobServiceImpl appliedJobService;
    @PostMapping("/add/{jobId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ApiResponse> applyForJob(@PathVariable("jobId") long jobId)
    {
        return ResponseEntity.ok().body(appliedJobService.applyJob(jobId));
    }
    @GetMapping("/myapplication")
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<AppliedJobResponse> getMyJobs(@Valid @RequestBody PageDTO pageDTO)
    {
        return appliedJobService.myAppliedJob(pageDTO);
    }
    @DeleteMapping("/delete/{applicationID}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ApiResponse> deleteApplication(@PathVariable("applicationID") long applicationId)
    {
        return ResponseEntity.ok().body(appliedJobService.deleteApplication(applicationId));
    }

}
