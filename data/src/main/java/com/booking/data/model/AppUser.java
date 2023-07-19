package com.booking.data.model;

import com.booking.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String securityQuestion;
    private String securityAnswer;
    private boolean isVerified;
    @OneToMany
    private List<Notification> notifications;
    private Role role;
    private boolean enabled;
    private String passwordResetToken;
    private String loginToken;
    private int failedLoginAttempt;
    private LocalDateTime timeLocked;
}
