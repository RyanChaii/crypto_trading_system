package com.personal.crypto.system.crypto_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CryptoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoServiceApplication.class, args);
	}

}
