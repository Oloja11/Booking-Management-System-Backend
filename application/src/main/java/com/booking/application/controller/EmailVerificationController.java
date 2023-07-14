package com.booking.application.controller;

import com.booking.sharedservice.emailverification.EmailVerificationService;
import com.booking.data.exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/learning-mgt/v1/auth")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/verify-email/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) throws BookingMgtException {
        return ResponseEntity.ok(emailVerificationService.verifyEMail(token));
    }

    @PostMapping("/resend-verification-email/{email}")
    public ResponseEntity<String> resendEmailVerificationEmail(@PathVariable String email) throws BookingMgtException {
        return ResponseEntity.ok(emailVerificationService.resendUserRegistrationOTP(email));
    }
}
