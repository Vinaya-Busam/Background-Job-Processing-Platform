package com.vinaya.jobplatform.service;

import org.springframework.stereotype.Service;
import com.vinaya.jobplatform.entity.Job;
import lombok.RequiredArgsConstructor;
import com.vinaya.jobplatform.repository.JobRepository;

@Service
@RequiredArgsConstructor 
public class JobService {

    private final JobRepository jobRepo;
   

    public Job submitJob(String type, String payload) {
        Job job = new Job(type, payload);
        return jobRepo.save(job);
    }

}
