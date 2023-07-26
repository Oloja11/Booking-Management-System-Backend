package com.booking.data.repository;

import com.booking.data.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long>{
}
