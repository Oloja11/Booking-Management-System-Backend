package com.booking.application.controller;


import com.booking.auth.services.authentication.AuthService;
import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.dto.request.LoginRequest;
import com.booking.data.model.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
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

//    @PostMapping("/verify-login/{otp}")
//    public ResponseEntity<Map<String, String>> verifyLogin(@PathVariable String otp) throws BookingMgtException {
//        return ResponseEntity.ok(authService.verifyLogin(otp));
//    }

    @PostMapping("/logout/{email}")
    public ResponseEntity<String> logout(@PathVariable String email) {
        return ResponseEntity.ok(authService.logout(email));
    }

//    @PostMapping("/resend-verify-login-otp/{email}")
//    public ResponseEntity<String> resendVerifyLoginOtp(@PathVariable String email) throws BookingMgtException {
//        return ResponseEntity.ok(authService.resendVerifyLoginOtp(email));
//    }
}
