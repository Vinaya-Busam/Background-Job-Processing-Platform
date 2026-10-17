package com.vinaya.jobplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class BackgroundJobProcessingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackgroundJobProcessingPlatformApplication.class, args);
	}

}
