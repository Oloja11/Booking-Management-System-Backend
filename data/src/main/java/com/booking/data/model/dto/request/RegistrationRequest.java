package com.booking.data.model.dto.request;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

public class RegistrationRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;
    private String firstName;
    private String lastName;
    private String confirmPassword;
    private String phoneNumber;

}
