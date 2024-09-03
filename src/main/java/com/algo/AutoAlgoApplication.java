package com.algo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AutoAlgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoAlgoApplication.class, args);
	}

}
