package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.EmailVerificationToken;
import com.jobhunter.jobhunterai.model.User;
import com.jobhunter.jobhunterai.repository.EmailVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import java.util.NoSuchElementException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailVerificationService {

    private static final int TOKEN_BYTES = 32;
    private static final long TOKEN_EXPIRATION_HOURS = 24;

    private final EmailVerificationTokenRepository tokenRepository;
    private final EmailService emailService;
    private final SecureRandom secureRandom;
    private final String baseUrl;

    public EmailVerificationService(
            EmailVerificationTokenRepository tokenRepository,
            EmailService emailService,
            @Value("${app.base-url}") String baseUrl) {

        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.secureRandom = new SecureRandom();
        this.baseUrl = baseUrl;
    }

    public String createVerificationToken(User user) {

        byte[] tokenBytes = new byte[TOKEN_BYTES];
        secureRandom.nextBytes(tokenBytes);

        String rawToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(tokenBytes);

        String tokenHash = hashToken(rawToken);

        EmailVerificationToken token = new EmailVerificationToken();

        token.setUser(user);
        token.setTokenHash(tokenHash);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(
                LocalDateTime.now().plusHours(TOKEN_EXPIRATION_HOURS)
        );

        tokenRepository.save(token);

        String verificationUrl =
                baseUrl + "/api/auth/verify-email?token=" + rawToken;

        emailService.sendVerificationEmail(
                user.getEmail(),
                verificationUrl
        );

        return rawToken;
    }

    private String hashToken(String token) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    token.getBytes(StandardCharsets.UTF_8)
            );

            return Base64.getEncoder()
                    .encodeToString(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 no está disponible",
                    e
            );
        }
    }

    @Transactional
    public void verifyEmail(String rawToken) {

        String tokenHash = hashToken(rawToken);

        EmailVerificationToken token = tokenRepository
                .findByTokenHash(tokenHash)
                .orElseThrow(() ->
                        new IllegalArgumentException("Token de verificación inválido"));

        if (token.getUsedAt() != null) {
            throw new IllegalArgumentException("Token de verificación ya utilizado");
        }

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token de verificación caducado");
        }

        User user = token.getUser();

        user.setEmailVerified(true);
        token.setUsedAt(LocalDateTime.now());
    }
}