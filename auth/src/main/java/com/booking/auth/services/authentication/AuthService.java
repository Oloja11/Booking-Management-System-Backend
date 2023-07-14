package com.booking.auth.services.authentication;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.dto.request.LoginRequest;
import com.booking.data.model.dto.response.LoginResponse;

import java.util.Map;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest) throws BookingMgtException;

    String logout(String email);

    Map<String, String> verifyLogin(String otp) throws BookingMgtException;

    String resendVerifyLoginOtp(String email) throws BookingMgtException;

}
