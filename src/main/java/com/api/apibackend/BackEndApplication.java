package com.api.apibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Validation;

@SpringBootApplication
@ComponentScan("com.api.apibackend")
@EnableCaching
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Bean
	public jakarta.validation.Validator validator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
}
