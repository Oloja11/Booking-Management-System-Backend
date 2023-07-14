package com.booking.sharedservice.emailverification;

import com.booking.data.exceptions.BookingMgtException;

public interface EmailVerificationService {
    String verifyEMail(String token) throws BookingMgtException;

    String resendUserRegistrationOTP(String email) throws BookingMgtException;

}
