package com.gildedrose.webapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gilded Rose Inventory API",
                version = "1.0.0",
                description = "REST API for managing Gilded Rose inventory and applying business rules daily.",
                contact = @Contact(name = "Gilded Rose Team", email = "support@example.com")
        )
)
public class OpenApiConfig {
}
