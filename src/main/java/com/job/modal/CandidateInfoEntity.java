package com.job.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@Entity
@Table(name = "candidate_info")
public class CandidateInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_info_id")
    private long candidateInfoID;
    @NotBlank
    @Column(name = "candidate_name")
    private String candidateName;
    @NotBlank
    @Column(name = "candidate_qualification")
    private String candidateQualification;
    @NotBlank
    @Column(name = "total_exp")
    private int totalExp;
    @NotBlank
    @Column(name = "current_ctc")
    private float currentCTC;
    @NotBlank
    @Column(name = "expected_ctc")
    private float expectedCTC;
    @NotBlank
    @Column(name = "notice_period")
    private int noticePeriod;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;
}
