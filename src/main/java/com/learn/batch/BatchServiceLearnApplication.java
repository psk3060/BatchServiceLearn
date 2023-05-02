package com.learn.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchServiceLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchServiceLearnApplication.class, args);
	}

}
