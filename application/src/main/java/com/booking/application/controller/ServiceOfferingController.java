package com.booking.application.controller;

import com.booking.BookingRequest;
import com.booking.ServceOfferingRequest;
import com.booking.ServiceOfferingAdapter;
import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.SecureUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/booking-mgt/v1/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {
    private final ServiceOfferingAdapter serviceOfferingAdapter;

    @PostMapping
    public ResponseEntity<?> createServiceOffering(@RequestBody ServceOfferingRequest servceOfferingRequest) throws BookingMgtException {
        return ResponseEntity.ok(serviceOfferingAdapter.createServiceOffering(servceOfferingRequest));
    }
    
    @PostMapping("/book/{serviceId}")
    public ResponseEntity<?> bookServiceOffering(@PathVariable String serviceId, @AuthenticationPrincipal SecureUser secureUser) throws BookingMgtException {
        return ResponseEntity.ok(serviceOfferingAdapter.bookServiceOffering(serviceId, secureUser));
    }


    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> getAllServiceOffering(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.ok(serviceOfferingAdapter.getAllServiceOffering(page, size));
    }
}
