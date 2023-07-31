package com.booking.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String bookingDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
