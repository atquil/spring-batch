package com.atquil.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	// If you want to run it without controller - self execute then use this
	/*
	public static void main(String[] args) throws Exception {
		System.exit(SpringApplication.exit(SpringApplication.run(SpringBatchApplication.class, args)));
	}
	*/

}
