package com.job.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private long jobId;
    @NotBlank
    @Column(name = "job_title")
    private String jobTitle;
    @NotBlank
    @Column(name = "job_description")
    @Size(max = 1000)
    private String jobDescription;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @OneToMany(mappedBy = "job")
    @JsonManagedReference
    private List<AppliedJobEntity> appliedJobs;
}
