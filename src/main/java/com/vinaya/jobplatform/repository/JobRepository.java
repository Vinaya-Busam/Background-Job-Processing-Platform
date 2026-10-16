package com.vinaya.jobplatform.repository;

import com.vinaya.jobplatform.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {


}
