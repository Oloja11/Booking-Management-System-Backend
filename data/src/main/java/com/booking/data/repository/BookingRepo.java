package com.booking.data.repository;

import com.booking.data.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long>{
    Page<Booking> findAllByUserEmail(String userEmail, Pageable pageable);
}
