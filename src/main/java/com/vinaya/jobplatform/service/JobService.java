package com.vinaya.jobplatform.service;

import org.springframework.stereotype.Service;
import com.vinaya.jobplatform.entity.Job;
import lombok.RequiredArgsConstructor;
import com.vinaya.jobplatform.repository.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor 
public class JobService {

    private final JobRepository jobRepo;
   

    // Submit a Job
    public Job submitJob(String type, String payload) {
        Job job = new Job(type, payload);
        return jobRepo.save(job);
    }


    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    
    // Get Job by Id
    public Optional<Job> getJobById(Long id) {
        return jobRepo.findById(id);
    }

}
