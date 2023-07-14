package com.booking.data.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class RegistrationResponse {
    private String message = "registered successfully";
    private String email;
}
