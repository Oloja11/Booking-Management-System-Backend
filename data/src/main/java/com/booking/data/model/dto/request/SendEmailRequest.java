package com.booking.data.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SendEmailRequest {
    private String emailAddress;
    private String message;
    private String subject;
}
