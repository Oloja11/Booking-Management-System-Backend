package com.booking.data.repository;

import com.booking.data.model.VerificationToken;
import com.booking.data.model.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {

    void deleteByEmailAndTokenType(String email, TokenType tokenType);

    Optional<VerificationToken> findByTokenAndTokenType(String token, TokenType tokenType);

    boolean existsByToken(String res);
}
