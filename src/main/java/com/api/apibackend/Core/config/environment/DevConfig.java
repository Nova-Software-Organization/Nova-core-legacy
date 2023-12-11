package com.api.apibackend.Core.config.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevConfig {
    public DevConfig() {
        System.out.println("Ambiente de desenvolvimento!!");
    }
}
