package com.job.service;

import com.job.payloads.CandidateInfoDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.ProfileResponse;

public interface CandidateService {

    public ApiResponse addProfile(CandidateInfoDTO candidateInfoDTO);
    public ProfileResponse getProfile();
    public ApiResponse updateProfile(long candidateInfoId,CandidateInfoDTO candidateInfoDTO);
}
