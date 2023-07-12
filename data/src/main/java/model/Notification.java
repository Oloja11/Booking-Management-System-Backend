package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    private Long id;
    private String header;
    private String body;
    private LocalDateTime timeAdded = LocalDateTime.now();
}
