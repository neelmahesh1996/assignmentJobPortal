package com.job.repository;

import com.job.modal.JobEntity;
import com.job.modal.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<JobEntity,Long> {
    @Override
    Page<JobEntity> findAll(Pageable pageable);
    Page<JobEntity> findByUser(UserEntity user, Pageable pageable);
}
