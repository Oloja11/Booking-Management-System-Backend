package com.booking.notification;

import com.booking.data.model.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("default")
@Slf4j
@Service
public class DevEmailService implements EmailService{
    @Override
    public void sendRegistrationEmail(AppUser appUser, String token) {
        log.info("email verification token => {}", token);
    }

    @Override
    public void sendPasswordResetEmail(AppUser user, String token) {
        log.info("password reset token => {}", token);
    }

    @Override
    public void sendEmailVerificationSuccessMessage(AppUser user) {

    }

    @Override
    public void sendPasswordResetSuccessMessage(AppUser user) {

    }

    @Override
    public void resendEmailVerificationOTP(AppUser user, String email) {

    }


    @Override
    public void sendLoginOtp(AppUser appUser, String token) {
        log.info("email verification token => {}", token);
    }
}
