package com.vinaya.jobplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank(message = "Job type is required")
    @Size(max = 100, message = "Job type must not exceed 100 characters")
    private String type;

    @NotBlank(message = "Job payload is required")
    private String payload;
}
