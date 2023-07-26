package com.booking.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Booking {
    @Id
    @UuidGenerator
    private String id;
    private BookingStatus bookingStatus;
    @OneToOne
    private AppUser appUser;
}
