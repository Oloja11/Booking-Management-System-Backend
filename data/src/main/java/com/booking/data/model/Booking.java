package com.booking.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Booking {
    @Id
    @UuidGenerator
    private String bookingId;
    private BookingStatus bookingStatus;
    private String userEmail;
    private LocalDateTime bokkingDate = LocalDateTime.now();
}
