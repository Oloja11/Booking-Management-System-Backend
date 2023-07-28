package com.booking.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    private ServiceOffering serviceOffering;
    private LocalDateTime bokkingDate = LocalDateTime.now();
}
