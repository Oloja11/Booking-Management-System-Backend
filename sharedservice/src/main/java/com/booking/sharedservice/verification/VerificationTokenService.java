package com.booking.sharedservice.verification;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.VerificationToken;
import com.booking.data.model.enums.TokenType;

public interface VerificationTokenService {

    VerificationToken createPasswordResetToken(String email);


    VerificationToken createRegistrationToken(String email);

    VerificationToken generateEMailVerificationToken(String email);

    void deleteToken(Long id);

    VerificationToken createLoginOtp(String email);

    VerificationToken verifyTokenAndFind(String token, TokenType emailVerification) throws BookingMgtException;

    void delete(String email, TokenType loginOtp);

}
