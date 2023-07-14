package com.booking.sharedservice.user;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.AppUser;
import com.booking.data.model.dto.request.RegistrationRequest;
import com.booking.data.model.dto.response.RegistrationResponse;

import java.util.List;

public interface UserService {
    AppUser findUserByEmail(String email) throws BookingMgtException;

    void saveUser(AppUser user);

    RegistrationResponse register(RegistrationRequest registrationRequest) throws BookingMgtException;

    boolean existByEmail(String email);

    List<AppUser> findAllDisabledUsers();

    void disableUser(AppUser appUser);

    void registerFailedLogin(AppUser appUser);
}
