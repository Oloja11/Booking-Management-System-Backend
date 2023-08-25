package com.booking.data.repository;

import com.booking.data.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long>{
    Page<Booking> findAllByUserEmail(String userEmail, Pageable pageable);
    boolean existsByUserEmailAndServiceOfferingId(String userEmail, String serviceOfferingId);
    List<Booking> findByUserEmailAndServiceOfferingId(String userEmail, String serviceOfferingId);
}
