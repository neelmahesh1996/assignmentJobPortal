package com.job.service.impl;

import com.job.modal.CandidateInfoEntity;
import com.job.payloads.CandidateInfoDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.ProfileResponse;
import com.job.repository.CandidateInfoRepo;
import com.job.repository.UserRepo;
import com.job.security.UserPrincipal;
import com.job.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateInfoRepo candidateInfoRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public ApiResponse addProfile(CandidateInfoDTO candidateInfoDTO) {

        try {
            UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            CandidateInfoEntity candidateInfo = new CandidateInfoEntity();

            candidateInfo.setCandidateName(candidateInfoDTO.getCandidateName());
            candidateInfo.setCandidateQualification(candidateInfoDTO.getCandidateQualification());
            candidateInfo.setTotalExp(candidateInfoDTO.getTotalExp());
            candidateInfo.setCurrentCTC(candidateInfoDTO.getCurrentCTC());
            candidateInfo.setExpectedCTC(candidateInfoDTO.getExpectedCTC());
            candidateInfo.setNoticePeriod(candidateInfoDTO.getNoticePeriod());
            candidateInfo.setUser(userRepo.findById(user.getUserId()).get());
            candidateInfoRepo.save(candidateInfo);
            return new ApiResponse(Boolean.TRUE,"profile add successfully");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }


    }

    @Override
    public ProfileResponse getProfile() {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CandidateInfoEntity candidateInfo=candidateInfoRepo.findByUser(userRepo.findById(user.getUserId()).get());
        return new ProfileResponse(candidateInfo.getCandidateName(), candidateInfo.getCandidateQualification(), candidateInfo.getTotalExp(), candidateInfo.getCurrentCTC(), candidateInfo.getExpectedCTC(), candidateInfo.getNoticePeriod());
    }

    @Override
    public ApiResponse updateProfile(long candidateInfoId, CandidateInfoDTO candidateInfoDTO) {
        try {
            UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            CandidateInfoEntity candidateInfo =candidateInfoRepo.findByUser(userRepo.findById(user.getUserId()).get());

            candidateInfo.setCandidateName(candidateInfoDTO.getCandidateName());
            candidateInfo.setCandidateQualification(candidateInfoDTO.getCandidateQualification());
            candidateInfo.setTotalExp(candidateInfoDTO.getTotalExp());
            candidateInfo.setCurrentCTC(candidateInfoDTO.getCurrentCTC());
            candidateInfo.setExpectedCTC(candidateInfoDTO.getExpectedCTC());
            candidateInfo.setNoticePeriod(candidateInfoDTO.getNoticePeriod());
            candidateInfo.setUser(userRepo.findById(user.getUserId()).get());
            candidateInfoRepo.save(candidateInfo);
            return new ApiResponse(Boolean.TRUE,"profile update successfully");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }
    }
}
