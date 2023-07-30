package com.thejackfolio.microservices.thejackfolio_db;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ThejackfolioDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThejackfolioDbApplication.class, args);
	}

}
