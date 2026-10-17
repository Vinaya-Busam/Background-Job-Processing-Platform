package com.vinaya.jobplatform.repository;

import com.vinaya.jobplatform.entity.Job;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*; 

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(String status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT j FROM Job j WHERE j.id = :id AND j.status = :status")
    Optional<Job> findByIdAndStatusForUpdate(@Param("id") Long id, @Param("status") String status);

}
