package com.job.payloads.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileResponse {
    private String candidateName;
    private String candidateQualification;
    private int totalExp;
    private float currentCTC;
    private float expectedCTC;
    private int noticePeriod;

    public ProfileResponse(String candidateName, String candidateQualification, int totalExp, float currentCTC, float expectedCTC, int noticePeriod) {
        this.candidateName = candidateName;
        this.candidateQualification = candidateQualification;
        this.totalExp = totalExp;
        this.currentCTC = currentCTC;
        this.expectedCTC = expectedCTC;
        this.noticePeriod = noticePeriod;
    }
}
