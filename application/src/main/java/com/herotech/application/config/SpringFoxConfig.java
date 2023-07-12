package com.herotech.application.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Objects;

public class SpringFoxConfig {

    @Value("${spring.datasource.url}")
    private String value;





    @Bean
    public OpenAPI appInfo() {

        Server server = new Server();
        if (Objects.equals(value, "jdbc:mysql://localhost/bookingMgt")) {
            server.setUrl("http://localhost:8090");
        }else server.setUrl("https://bookingmgt.onrender.com");
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .servers(List.of(server))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info().title("Booking Management API").version("1.0"));
    }


    @Bean
    public GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .group("https")
                .pathsToMatch("/**")
                .build();
    }


}
