package com.example.FirstApplicationSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class FirstApplicationSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstApplicationSpringApplication.class, args);
			//System.out.println(new BCryptPasswordEncoder().encode("test1234"));
		}

	}

