package com.job.modal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @NotBlank
    @Column(name = "username")
    @Size(max = 40)
    private String username;

    @NotBlank
    @Column(name = "password",length = 2000)
    private String password;

    @NotBlank
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private CandidateInfoEntity candidateInfo;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<JobEntity> jobs;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<AppliedJobEntity> appliedJobs;






}
