package com.example.FirstApplicationSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FirstApplicationSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstApplicationSpringApplication.class, args);
	}

}
