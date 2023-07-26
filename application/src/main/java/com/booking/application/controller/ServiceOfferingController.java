package com.booking.application.controller;

import com.booking.ServiceOfferingAdapter;
import com.booking.ServiceOfferingRequest;
import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.SecureUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/booking-mgt/v1/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {
    private final ServiceOfferingAdapter serviceOfferingAdapter;

    @PostMapping
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<?> createServiceOffering(@RequestBody ServiceOfferingRequest serviceOfferingRequest, @AuthenticationPrincipal SecureUser secureUser) throws BookingMgtException {
        return ResponseEntity.ok(serviceOfferingAdapter.createServiceOffering(serviceOfferingRequest, secureUser));
    }

    @PostMapping("/book/{serviceId}")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<?> bookServiceOffering(@PathVariable String serviceId, @AuthenticationPrincipal SecureUser secureUser) throws BookingMgtException {
        return ResponseEntity.ok(serviceOfferingAdapter.bookServiceOffering(serviceId, secureUser));
    }

       @PostMapping("/cancel/{serviceId}/{userEmail}")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<?> acceptBooking(@PathVariable String serviceId, @PathVariable String userEmail) throws BookingMgtException {
        return ResponseEntity.ok(serviceOfferingAdapter.acceptBooking(serviceId, userEmail));
    }


    @PostMapping("/cancel/{serviceId}/{userEmail}")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<?> cancelBooking(@PathVariable String serviceId, @PathVariable String userEmail) throws BookingMgtException {
        return ResponseEntity.ok(serviceOfferingAdapter.cancelBooking(serviceId, userEmail));
    }


    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> getAllServiceOffering(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.ok(serviceOfferingAdapter.getAllServiceOffering(page, size));
    }
}
