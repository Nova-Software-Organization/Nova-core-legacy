package com.api.apibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.api.apibackend.Core.config.environment.DevConfig;
import com.api.apibackend.Core.config.environment.ProdConfig;

import jakarta.validation.Validation;

@EnableCaching
@SpringBootApplication
@ComponentScan("com.api.apibackend")
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
    
	@Bean
    @Profile("dev")
    public DevConfig devBean() {
        return new DevConfig();
    }

    @Bean
    @Profile("prod")
    public ProdConfig prodBean() {
        return new ProdConfig();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
