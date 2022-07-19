package com.job.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@Table(name="applied_job")
public class AppliedJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applied_job_id")
    private long appliedJobId;


    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity job;

}
