package com.booking.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.booking.**")
@EntityScan(basePackages = "com.booking.**")
@EnableJpaRepositories(basePackages = "com.booking.**")
@EnableAsync
@EnableScheduling
public class BookingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }

}
