package com.vinaya.jobplatform.controller;

import com.vinaya.jobplatform.dto.JobRequest;
import com.vinaya.jobplatform.entity.Job;
import com.vinaya.jobplatform.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor 
public class JobController {

    private final JobService jobService;

    // Submit Job
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job submitJob(@Valid @RequestBody JobRequest request) {
        return jobService.submitJob(
                request.getType(),
                request.getPayload()
        );
    }

    // Get All Jobs
    @GetMapping("/getAllJobs") 
    @ResponseStatus(HttpStatus.OK)
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Get Job By Id
    @GetMapping("/getJob/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                            .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Job not found with id: " + id
                            ));
    }
}
