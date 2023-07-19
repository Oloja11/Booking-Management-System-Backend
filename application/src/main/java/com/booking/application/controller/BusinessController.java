package com.booking.application.controller;

import com.booking.BusinessRequest;
import com.booking.BusinessService;
import com.booking.data.exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/booking-mgt/v1/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<String> createBusiness(@RequestBody BusinessRequest businessRequest) throws BookingMgtException {
        return ResponseEntity.ok(businessService.createBusiness(businessRequest));
    }
}
