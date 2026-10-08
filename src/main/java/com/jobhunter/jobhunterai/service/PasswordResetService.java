package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.PasswordResetToken;
import com.jobhunter.jobhunterai.model.User;
import com.jobhunter.jobhunterai.repository.PasswordResetTokenRepository;
import com.jobhunter.jobhunterai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.jobhunter.jobhunterai.exception.PasswordResetException;

@Service
public class PasswordResetService {

    private static final int TOKEN_BYTES = 32;
    private static final long TOKEN_EXPIRATION_HOURS = 1;

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SecureRandom secureRandom;
    private final String baseUrl;

    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(
            PasswordResetTokenRepository tokenRepository,
            UserRepository userRepository,
            EmailService emailService,
            PasswordEncoder passwordEncoder,
            @Value("${app.base-url}") String baseUrl) {

        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.secureRandom = new SecureRandom();
        this.baseUrl = baseUrl;
    }

    public void requestPasswordReset(String email) {

        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            return;
        }

        String rawToken = createResetToken(user);

        String resetUrl =
                baseUrl + "/api/auth/reset-password?token=" + rawToken;

        emailService.sendPasswordResetEmail(
                user.getEmail(),
                resetUrl
        );
    }

    private String createResetToken(User user) {

        byte[] tokenBytes = new byte[TOKEN_BYTES];
        secureRandom.nextBytes(tokenBytes);

        String rawToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(tokenBytes);

        String tokenHash = hashToken(rawToken);

        PasswordResetToken token = new PasswordResetToken();

        token.setUser(user);
        token.setTokenHash(tokenHash);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(
                LocalDateTime.now().plusHours(TOKEN_EXPIRATION_HOURS)
        );

        tokenRepository.save(token);

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
    public void resetPassword(String rawToken, String newPassword) {

        String tokenHash = hashToken(rawToken);

        PasswordResetToken token = tokenRepository
                .findByTokenHash(tokenHash)
                .orElseThrow(() ->
                        new PasswordResetException(
                                "Token de recuperación inválido"
                        ));

        if (token.getUsedAt() != null) {
            throw new PasswordResetException(
                    "Token de recuperación ya utilizado"
            );
        }

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new PasswordResetException(
                    "Token de recuperación caducado"
            );
        }

        User user = token.getUser();

        user.setPasswordHash(
                passwordEncoder.encode(newPassword)
        );

        token.setUsedAt(LocalDateTime.now());
    }
}