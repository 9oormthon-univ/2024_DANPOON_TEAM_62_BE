package com.example.region;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com/example/region"})
public class regionApplication {

	public static void main(String[] args) {
		SpringApplication.run(regionApplication.class, args);
	}

}
