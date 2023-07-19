package com.booking.data.repository;

import com.booking.data.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, String> {
    boolean existsByName(String name);
}
