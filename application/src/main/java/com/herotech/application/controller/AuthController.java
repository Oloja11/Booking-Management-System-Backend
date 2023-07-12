package com.herotech.application.controller;

import auth.services.authentication.AuthService;
import exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import model.dto.request.LoginRequest;
import model.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/booking-mgt/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws BookingMgtException{
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/verify-login/{otp}")
    public ResponseEntity<Map<String, String>> verifyLogin(@PathVariable String otp) throws BookingMgtException {
        return ResponseEntity.ok(authService.verifyLogin(otp));
    }

    @PostMapping("/logout/{email}")
    public ResponseEntity<String> logout(@PathVariable String email) throws BookingMgtException {
        return ResponseEntity.ok(authService.logout(email));
    }

    @PostMapping("/resend-verify-login-otp/{email}")
    public ResponseEntity<String> resendVerifyLoginOtp(@PathVariable String email) throws BookingMgtException {
        return ResponseEntity.ok(authService.resendVerifyLoginOtp(email));
    }
}
