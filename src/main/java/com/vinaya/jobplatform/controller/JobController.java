package com.vinaya.jobplatform.controller;

import com.vinaya.jobplatform.dto.JobRequest;
import com.vinaya.jobplatform.entity.Job;
import com.vinaya.jobplatform.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor 
public class JobController {

    private final JobService jobService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job submitJob(@Valid @RequestBody JobRequest request) {
        return jobService.submitJob(
                request.getType(),
                request.getPayload()
        );
    }
}
