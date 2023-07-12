package emailverification;

import exceptions.BookingMgtException;

public interface EmailVerificationService {
    String verifyEMail(String token) throws BookingMgtException;

    String resendUserRegistrationOTP(String email) throws BookingMgtException;

}
