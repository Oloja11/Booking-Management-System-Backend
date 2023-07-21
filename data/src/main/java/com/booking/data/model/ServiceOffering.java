package com.booking.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ServiceOffering {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String description;
    private String businessId;
    private String imageUrl;
    @OneToMany
    private List<AppUser> bookedBy;
}
