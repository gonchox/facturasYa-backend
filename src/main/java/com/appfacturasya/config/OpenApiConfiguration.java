package com.appfacturasya.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "facturasyaOpenApi")
    public OpenAPI facturasyaOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("FacturasYa Application API")
                        .description("FacturasYa API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));
    }

    }


