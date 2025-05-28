package com.entrega.entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "User Management API",
        version = "1.0",
        description = "API for user management with JWT authentication"
    )
)
public class EntregaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EntregaApplication.class, args);
    }
} 