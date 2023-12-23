package com.api.apibackend.Core.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("ikauedeveloper@gmail.com");
        contact.setName("Kauê de Matos Oliveira");
        contact.setUrl("");

        Info info = new Info()
                .title("Nova Core microservice")
                .version("1.0")
                .description("Microservice.")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
