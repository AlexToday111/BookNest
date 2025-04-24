package com.booknest.common.error;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI bookNestOpenApi(
            @Value("${booknest.api.title}") String title,
            @Value("${booknest.api.version}") String version,
            @Value("${booknest.api.description}") String description
    ) {
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(description));
    }
}
