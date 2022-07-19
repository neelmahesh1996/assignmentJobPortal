package com.job.repository;

import com.job.modal.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
