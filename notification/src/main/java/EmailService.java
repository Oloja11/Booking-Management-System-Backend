import model.AppUser;

public interface EmailService {
    void sendRegistrationEmail(AppUser appUser, String token);

    void sendPasswordResetEmail(AppUser user,  String token);

    void sendEmailVerificationSuccessMessage(AppUser user);

    void sendPasswordResetSuccessMessage(AppUser user);

    void resendEmailVerificationOTP(AppUser user, String email);

    void  sendEnrollmentSuccessMessage(AppUser user,String course);

    void sendLoginOtp(AppUser appUser, String otp);

}
