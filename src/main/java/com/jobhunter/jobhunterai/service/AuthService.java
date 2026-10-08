package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.dto.RegisterRequest;
import com.jobhunter.jobhunterai.model.User;
import com.jobhunter.jobhunterai.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationService emailVerificationService;
    private final PasswordResetService passwordResetService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            EmailVerificationService emailVerificationService,
            PasswordResetService passwordResetService,
            AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailVerificationService = emailVerificationService;
        this.passwordResetService = passwordResetService;
        this.authenticationManager = authenticationManager;
    }

    public User register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        String passwordHash = passwordEncoder.encode(request.getPassword());
        user.setPasswordHash(passwordHash);

        User savedUser = userRepository.save(user);

        emailVerificationService.createVerificationToken(savedUser);

        return savedUser;
    }

    public void verifyEmail(String token) {
        emailVerificationService.verifyEmail(token);
    }

    public void requestPasswordReset(String email) {
        passwordResetService.requestPasswordReset(email);
    }

    public void resetPassword(String token, String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
    }

    public Authentication login(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        email,
                        password
                );

        return authenticationManager.authenticate(authenticationToken);
    }
}