package com.booking.data.repository;

import com.booking.data.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, String> {
    boolean existsByName(String name);


    Optional<Business> findByCreatorId(long creatorId);
}
