package com.job.repository;

import com.job.modal.CandidateInfoEntity;
import com.job.modal.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateInfoRepo extends JpaRepository<CandidateInfoEntity,Long> {

    CandidateInfoEntity findByUser(UserEntity user);
}
