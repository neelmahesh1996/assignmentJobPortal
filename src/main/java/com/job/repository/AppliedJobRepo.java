package com.job.repository;

import com.job.modal.AppliedJobEntity;
import com.job.modal.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppliedJobRepo extends JpaRepository<AppliedJobEntity,Long> {
    @Override
    Page<AppliedJobEntity> findAll(Pageable pageable);

    Page<AppliedJobEntity> findByUser(UserEntity user,Pageable pageable);
}
