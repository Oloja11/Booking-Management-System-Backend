package com.booking.auth.services.authentication;

import com.booking.auth.services.jwt.JwtService;
import com.booking.data.model.enums.Role;
import com.booking.notification.EmailService;
import com.booking.data.exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.booking.data.model.AppUser;
import com.booking.data.model.SecureUser;
import com.booking.data.model.VerificationToken;
import com.booking.data.model.dto.request.LoginRequest;
import com.booking.data.model.dto.response.LoginResponse;
import com.booking.data.model.enums.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.booking.sharedservice.user.UserService;
import com.booking.sharedservice.verification.VerificationTokenService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final VerificationTokenService verificationTokenService;
    private final UserService userService;
    @Value("${bms.app.loginAttempts}")
    private int LOGIN_ATTEMPTS;
    @Value("${bms.app.lockedDuration}")
    private int ACCOUNT_LOCK_DURATION;


    @Override
    public LoginResponse login(LoginRequest request) throws BookingMgtException {
        AppUser appUser = userService.findUserByEmail(request.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            if (appUser.getFailedLoginAttempt() == LOGIN_ATTEMPTS - 1) {
                userService.disableUser(appUser);
            } else {
                userService.registerFailedLogin(appUser);
            }
            throw new BookingMgtException("Invalid username or password");
        } catch (DisabledException ex) {
            if (appUser.getFailedLoginAttempt() == LOGIN_ATTEMPTS - 1) sendAccountLockedErrorMessage(appUser);
            throw new BookingMgtException("please verify your email");
        }
        String jwtToken = performSuccessLogin(appUser);
        return LoginResponse.of(jwtToken, appUser.getId(), appUser);
    }


    private String performSuccessLogin(AppUser appUser) {
        appUser.setFailedLoginAttempt(0);
        userService.saveUser(appUser);
        verificationTokenService.delete(appUser.getEmail(), TokenType.LOGIN_OTP);
        SecureUser user = new SecureUser(appUser);
        String jwtToken = jwtService.generateToken(user);
        if (appUser.getRole() != Role.CUSTOMER) {
            VerificationToken verificationToken = verificationTokenService.createLoginOtp(appUser.getEmail());
            emailService.sendLoginOtp(appUser, verificationToken.getToken());
        }
        return jwtToken;
    }

    private void sendAccountLockedErrorMessage(AppUser appUser) throws BookingMgtException {
        long minutes = ChronoUnit.MINUTES.between(appUser.getTimeLocked(), LocalDateTime.now());
        throw new BookingMgtException(String.format("Too many login attempts. account locked. try again after %d minutes", ACCOUNT_LOCK_DURATION - minutes));
    }

    @Override
    public String logout(String email) {
        return "Logout Successful";
    }

    @Override
    public Map<String, String> verifyLogin(String otp) throws BookingMgtException {
        VerificationToken verificationToken = verificationTokenService.verifyTokenAndFind(otp, TokenType.LOGIN_OTP);
        verificationTokenService.deleteToken(verificationToken.getId());
        AppUser appUser = userService.findUserByEmail(verificationToken.getEmail());
        return Map.of(
                "message", "login verified",
                "role",appUser.getRole().name()
                );
    }

    @Override
    public String resendVerifyLoginOtp(String email) throws BookingMgtException {
        verificationTokenService.delete(email, TokenType.LOGIN_OTP);
        VerificationToken verificationToken = verificationTokenService.createLoginOtp(email);
        AppUser user = userService.findUserByEmail(email);
        emailService.sendLoginOtp(user, verificationToken.getToken());
        return "verify login email resent successfully";
    }

    @Scheduled(cron = "0 */1 * ? * *")
    public void enableAccount() {
        List<AppUser> disabledUsers = userService.findAllDisabledUsers();
        if (disabledUsers.isEmpty()) return;
        disabledUsers.forEach(user -> {
            if (user.isVerified() && ChronoUnit.MINUTES.between(user.getTimeLocked(), LocalDateTime.now()) >= ACCOUNT_LOCK_DURATION) {
                user.setEnabled(true);
                user.setFailedLoginAttempt(0);
                user.setTimeLocked(null);
                userService.saveUser(user);
            }
        });
    }

}
