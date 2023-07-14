package com.booking.application.controller;

import com.booking.data.exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import com.booking.data.model.dto.request.RegistrationRequest;
import com.booking.data.model.dto.response.RegistrationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.booking.sharedservice.user.UserService;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/booking-mgt/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
        public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest registrationRequest) throws BookingMgtException {
        return new ResponseEntity<>(userService.register(registrationRequest), HttpStatus.CREATED);
    }
}
