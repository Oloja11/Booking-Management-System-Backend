package com.booking.sharedservice.verification;


import com.booking.data.exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import com.booking.data.model.VerificationToken;
import com.booking.data.model.enums.TokenType;
import com.booking.data.repository.VerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VerificationTokenServiceImpl implements  VerificationTokenService {
    private final VerificationRepository verificationTokenRepository;

    @Override
    public VerificationToken createPasswordResetToken(String email) {
        return verificationTokenRepository.save(
                new VerificationToken(
                        UUID.randomUUID().toString(),
                        LocalDateTime.now().plusHours(1L),
                        email,
                        TokenType.PASSWORD_RESET
                )
        );
    }


    @Override
    public void deleteToken(Long id) {
        verificationTokenRepository.deleteById(id);
    }

    @Override
    public VerificationToken createLoginOtp(String email) {
        return verificationTokenRepository.save(
                new VerificationToken(
                        getSixDigitToken(),
                        LocalDateTime.now().plusMinutes(5),
                        email,
                        TokenType.LOGIN_OTP
                )
        );
    }

    @Override
    @Transactional
    public void delete(String email, TokenType tokenType) {
        verificationTokenRepository.deleteByEmailAndTokenType(email, tokenType);
    }



    @Override
    public VerificationToken verifyTokenAndFind(String token, TokenType tokenType) throws  BookingMgtException {
        VerificationToken verificationToken = verificationTokenRepository.findByTokenAndTokenType(token, tokenType).orElseThrow(
                () -> new BookingMgtException("invalid or expired")
        );
        if (verificationToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BookingMgtException("This token has expired!!");
        }
        return verificationToken;
    }

    public String getSixDigitToken() {
        SecureRandom secureRandom = new SecureRandom();
//        Random rnd = new Random();
        int number = secureRandom.nextInt(999999);
        String res =  String.format("%06d", number);
        System.out.println(res);
        if(verificationTokenRepository.existsByToken(res)){
            return getSixDigitToken();
        }
        System.out.println("leaving baby");
        return res;
    }

    @Override
    public VerificationToken generateEMailVerificationToken(String email) {
        return createRegistrationToken(email);
    }


    @Override
    public VerificationToken createRegistrationToken(String email) {
        String rawToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                rawToken,
                LocalDateTime.now().plusHours(24),
                email,
                TokenType.EMAIL_VERIFICATION

        );
        return verificationTokenRepository.save(verificationToken);
    }
}
