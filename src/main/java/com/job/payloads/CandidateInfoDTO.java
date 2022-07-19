package com.job.payloads;
import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class CandidateInfoDTO {

    @NotBlank(message = "candidate name should not be blank")
    private String candidateName;
    @NotBlank(message = "candidate qualification should not be blank")
    private String candidateQualification;
    @NotBlank(message = "candidate total experience should not be blank")
    private int totalExp;
    @NotBlank(message = "candidate current CTC should not be blank")
    private float currentCTC;
    @NotBlank(message = "candidate expected CTC should not be blank")
    private float expectedCTC;
    @NotBlank(message = "candidate notice period should not be blank")
    private int noticePeriod;
}
