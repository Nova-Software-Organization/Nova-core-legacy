package com.api.apibackend.core.server;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortRandomConfiguration {
      @Bean
        public int randomServerPort() {
            return new Random().nextInt(9000 - 8080) + 8080;
        }
}
