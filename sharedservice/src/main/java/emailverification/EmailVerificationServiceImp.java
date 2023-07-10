package emailverification;


import exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import model.AppUser;
import model.VerificationToken;
import model.enums.TokenType;
import org.springframework.stereotype.Service;
import user.UserService;
import verification.VerificationTokenService;

import java.awt.print.Book;

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
