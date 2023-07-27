package com.booking.application.controller;

import com.booking.BusinessRequest;
import com.booking.BusinessService;
import com.booking.data.exceptions.BookingMgtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/booking-mgt/v1/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<String> createBusiness(@RequestBody @Valid BusinessRequest businessRequest) throws BookingMgtException {
        return ResponseEntity.ok(businessService.createBusiness(businessRequest));
    }
}
