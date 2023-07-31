package com.booking.application.utils;

import com.booking.data.model.AppUser;
import com.booking.data.model.Business;
import com.booking.data.model.enums.Role;
import com.booking.data.repository.BusinessRepository;
import com.booking.data.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DummyObjs {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void registerBusiness() {
        if (!businessRepository.existsByName("Doe Cuts")) {
            AppUser appUser = AppUser.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("johndoe@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .enabled(true)
                    .role(Role.BUSINESS)
                    .isVerified(true)
                    .build();
            AppUser savesUser = userRepository.save(appUser);
            Business business = Business.builder()
                    .creatorId(savesUser.getId())
                    .name("Doe Cuts")
                    .description("desc")
                    .industry("desc")

                    .build();
            businessRepository.save(business);
        }

        if (!userRepository.existsByEmail("janewillis@mail.com")) {
            AppUser appUser = AppUser.builder()
                    .firstName("Jane")
                    .lastName("Willis")
                    .email("janewillis@mail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.USER)
                    .build();
            userRepository.save(appUser);
        }
    }
}


