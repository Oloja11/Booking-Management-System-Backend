package com.booking.data.repository;

import com.booking.data.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, String> {
}
