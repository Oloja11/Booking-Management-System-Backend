package com.booking.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Business {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String name;
    private String description;
    private String industry;
    private long creatorId;
    private String userId;

}
