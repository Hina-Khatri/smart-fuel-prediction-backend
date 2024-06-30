package com.bugfixers.smartfuelprediction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SmartfuelpredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartfuelpredictionApplication.class, args);
	}

}
