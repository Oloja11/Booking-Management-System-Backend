package com.booking.auth.config;

import com.booking.auth.services.jwt.JwtAuthenticationFilter;
import com.booking.auth.utils.ExceptionHandler;
import com.booking.auth.utils.UnAuthorizedEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final UnAuthorizedEntryPoint unAuthorizedEntryPoint;
    private final String[] NO_AUTH_ROUTES = {
            "/api/booking-mgt/v1/user/sign-up", "/api/booking-mgt/v1/auth/**",
            "/api/booking-mgt/v1/business",
            "api/booking-mgt/v1/service-offering",
            // swagger ui docs
            "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(NO_AUTH_ROUTES)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unAuthorizedEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilterBean(), JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public ExceptionHandler exceptionHandlerFilterBean() {
        return new ExceptionHandler();
    }

}
