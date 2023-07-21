package com.booking.application.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Objects;


@Configuration
@EnableWebMvc
public class SpringFoxConfig {

    @Value("${spring.datasource.url}")
    private String value;





    @Bean
    public OpenAPI appInfo() {

        Server server = new Server();
         server.setUrl("https://booking-management-production.up.railway.app");
         Server server1 = new Server();
            server1.setUrl("http://localhost:8080");
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .servers(List.of(server,server1))
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
