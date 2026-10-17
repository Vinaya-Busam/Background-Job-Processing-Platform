package com.vinaya.jobplatform.worker;

import com.vinaya.jobplatform.entity.Job;
import com.vinaya.jobplatform.repository.JobRepository;
import com.vinaya.jobplatform.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor 
@Slf4j
public class JobWorker {

    private final JobRepository jobRepo;
    private final JobService jobService;


    @Scheduled(fixedDelay = 5000)
    public void processPendingJobs() {
        List<Job> pendingJobs = jobRepo.findByStatus("PENDING");

        for(Job job : pendingJobs) {
            Optional<Job> claimedJob = jobService.claimJob(job.getId());
            if(claimedJob.isEmpty()) {
                continue;
            }
            processClaimedJob(claimedJob.get()); 
        }
    }

    
    public void processClaimedJob(Job job) {
        try {
            log.info("Processing job with ID: {}", job.getId());

            executeJob(job);

            job.setStatus("COMPLETED");
            jobRepo.save(job);

            log.info("Job {} completed successfully", job.getId());

        } catch (Exception e) {
            job.setStatus("FAILED");
            jobRepo.save(job);

            log.error("Job {} failed", job.getId(), e);
        }
    }

    private void executeJob(Job job) {
        log.info("Executing job type: {}", job.getType());

        // We'll add real job-type-specific execution here later.
    }
}
