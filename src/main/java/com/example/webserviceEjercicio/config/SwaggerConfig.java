package com.example.webserviceEjercicio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Productos con MongoDB")
                        .version("1.0")
                        .description("Documentación de la API de gestión de productos usando OpenAPI y Swagger")
                        .contact(new Contact()
                                .name("Soporte API")
                                .email("ajperezmarin@gmail.com")
                                .url("https://example.com")));
    }
}
