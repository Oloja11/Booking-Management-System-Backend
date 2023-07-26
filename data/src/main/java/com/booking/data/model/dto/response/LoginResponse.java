package com.booking.data.model.dto.response;


import com.booking.data.model.AppUser;
import com.booking.data.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class LoginResponse {

    private String jwtToken;
    private Long userId;
    private final String message = "login Successful";
    private String firstName;
    private String lastName;
    private  String email;
    private Role role;
    public static LoginResponse of(String jwtToken, Long userId, AppUser appUser) {
        return new LoginResponse(jwtToken, userId, appUser.getFirstName(), appUser.getLastName(), appUser.getEmail(), appUser.getRole());
    }
}
