package com.booking.notification;

import com.booking.data.model.AppUser;
import com.booking.data.model.dto.request.SendEmailRequest;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.util.StringUtils.capitalize;


@Service
@RequiredArgsConstructor
@Slf4j
@Profile("!default")
public class ProdEmailService implements  EmailService {

    @Value("${bms.app.frontend-host}")
    private String frontEndHost;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    private final ResourceLoader resourceLoader;

    @Override
    public void sendRegistrationEmail(AppUser user, String token) {

        sendEmail(SendEmailRequest.builder()
                .emailAddress(user.getEmail())
                .subject("Verification for your BMS account")
                .message(String.format(readHtmlFile("welcome.html"),
                        capitalize(user.getFirstName().toLowerCase()),
                        frontEndHost + "verify_email/" + token))
                .build());

    }


    @Override
    public void sendPasswordResetEmail(AppUser user, String token) {
        String link = """
                Otherwise, please click this <a href="%s">link</a> to change your password
                """;

        String message = String.format("""
                There was a request to change your password!
                If you did not make this request, please ignore this email.
                        """ + link, frontEndHost + "reset_password/" + token);

    }

    @Override
    public void sendEmailVerificationSuccessMessage(AppUser user) {
        sendEmail(SendEmailRequest.builder()
                .emailAddress(user.getEmail())
                .subject("EMAIL VERIFIED SUCCESSFULLY - BOOKING MANAGEMENT")
                .message("""
                        your email has been successfully verified, click <a href="%s">HERE</a> to login
                                """.formatted(frontEndHost + "login"))
                .build());
    }

    @Override
    public void sendPasswordResetSuccessMessage(AppUser user) {

        String  message = String.format(readHtmlFile("reset.html"),capitalize(user.getFirstName().toLowerCase() ));
        sendEmail(SendEmailRequest.builder()
                .emailAddress(user.getEmail())
                .subject("PASSWORD RESET SUCCESS -  BOOKING MANAGEMENT")
                .message(message)
                .build());
    }

    @Override
    public void resendEmailVerificationOTP(AppUser user, String token) {
        sendEmail(SendEmailRequest.builder()
                .emailAddress(user.getEmail())
                .subject("Verify your Email  - BOOKING MANAGEMENT")
                .message("""
                        WELCOME TO BOOKING MANAGEMENT
                        click on this link to verify your email
                                 """ + frontEndHost + "verify_email/" + token)
                .build());
    }


    @Override
    public void sendLoginOtp(AppUser appUser, String otp) {
        sendEmail(SendEmailRequest.builder()
                .emailAddress(appUser.getEmail())
                .subject("Your One-Time Password (OTP) ")
                .message(readHtmlFile("otp.html").formatted(capitalize(appUser.getFirstName().toLowerCase()), otp))
                .build());
    }

    @Async
    public void sendEmail(SendEmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(emailRequest.getMessage(), true);
            helper.setTo(emailRequest.getEmailAddress());
            helper.setFrom(sender);
            helper.setSubject(emailRequest.getSubject());

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error while Sending Mail");
        }

    }


    public String readHtmlFile(String file) {
        try {
            InputStream inputStream = resourceLoader.getResource("classpath:" + file).getInputStream();
            return new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }


}
