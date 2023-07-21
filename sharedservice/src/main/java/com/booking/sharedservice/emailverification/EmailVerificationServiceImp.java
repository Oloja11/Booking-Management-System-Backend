package com.booking.sharedservice.emailverification;


import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.AppUser;
import com.booking.data.model.VerificationToken;
import com.booking.data.model.enums.TokenType;
import com.booking.notification.EmailService;
import com.booking.sharedservice.user.UserService;
import com.booking.sharedservice.verification.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImp implements EmailVerificationService{

    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final UserService userService;
    @Override
    public String verifyEMail(String token) throws BookingMgtException {
        VerificationToken verificationToken = verificationTokenService
                .verifyTokenAndFind(token, TokenType.EMAIL_VERIFICATION);
        AppUser user = userService.findUserByEmail(verificationToken.getEmail());
        user.setVerified(true);
       user.setEnabled(true);
        userService.saveUser(user);
        emailService.sendEmailVerificationSuccessMessage(user);
        verificationTokenService.deleteToken(verificationToken.getId());
        return "Email verified Successfully";
    }

    @Override
    public String resendUserRegistrationOTP(String email) throws BookingMgtException {
        AppUser user = userService.findUserByEmail(email);
        if (user.isVerified()) throw new BookingMgtException("user is verified, proceed to login");
        verificationTokenService.delete(email, TokenType.EMAIL_VERIFICATION);
        VerificationToken verificationToken =
                verificationTokenService.generateEMailVerificationToken(email);
        emailService.resendEmailVerificationOTP(user, verificationToken.getToken());
        return "email re-sent successfully";
    }
}
