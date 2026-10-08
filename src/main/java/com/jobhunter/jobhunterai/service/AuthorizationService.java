package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.MasterProfile;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public void checkMasterProfileOwnership(MasterProfile masterProfile) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String authenticatedEmail = authentication.getName();

        String ownerEmail = masterProfile.getUser().getEmail();

        if (!authenticatedEmail.equals(ownerEmail)) {
            throw new AccessDeniedException("Acceso denegado");
        }
    }
}