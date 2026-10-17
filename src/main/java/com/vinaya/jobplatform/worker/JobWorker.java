package com.vinaya.jobplatform.worker;

import com.vinaya.jobplatform.entity.Job;
import com.vinaya.jobplatform.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor 
@Slf4j
public class JobWorker {

    private final JobRepository jobRepo;


    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processPendingJobs() {
        List<Job> pendingJobs = jobRepo.findByStatus("PENDING");

        for(Job job : pendingJobs) {
            try {
                job.setStatus("PROCESSING");
                jobRepo.save(job);

                log.info("Processing job with ID: {}", job.getId());

                // Temporary execution logic
                executeJob(job);

                job.setStatus("COMPLETED");
                jobRepo.save(job);

                log.info("Job {} completed successfully", job.getId());
            } catch(Exception e) {
                job.setStatus("FAILED");
                jobRepo.save(job);

                log.error("Job {} failed", job.getId());
            }
        }
    }

    private void executeJob(Job job) {
        log.info("Executing job type: {}", job.getType());

        // We'll add real job-type-specific execution here later.
    }
}
