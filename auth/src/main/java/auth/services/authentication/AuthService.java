package auth.services.authentication;

import exceptions.BookingMgtException;
import model.dto.request.LoginRequest;
import model.dto.response.LoginResponse;

import java.util.Map;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest) throws BookingMgtException;

    String logout(String email);

    Map<String, String> verifyLogin(String otp) throws BookingMgtException;

    String resendVerifyLoginOtp(String email) throws BookingMgtException;

}
