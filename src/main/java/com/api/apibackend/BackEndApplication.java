/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.api.apibackend.core.config.environment.DevConfig;
import com.api.apibackend.core.config.environment.ProdConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Validation;

@EnableCaching
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Nova Core",
        description = "API principal do Nova Core responsavel pela entrada de pedidos dentro aplicação chamada de serviços externos da propria Nova Software caso necessário",
        version = "1.0.1"
    )
)
@SecurityScheme(name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
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
