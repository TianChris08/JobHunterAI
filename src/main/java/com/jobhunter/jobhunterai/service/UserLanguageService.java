package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.dto.UserLanguageRequest;
import com.jobhunter.jobhunterai.dto.UserLanguageResponse;
import com.jobhunter.jobhunterai.model.MasterProfile;
import com.jobhunter.jobhunterai.model.UserLanguage;
import com.jobhunter.jobhunterai.repository.MasterProfileRepository;
import com.jobhunter.jobhunterai.repository.UserLanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserLanguageService {

    private final UserLanguageRepository userLanguageRepository;
    private final MasterProfileRepository masterProfileRepository;

    public UserLanguageService(
            UserLanguageRepository userLanguageRepository,
            MasterProfileRepository masterProfileRepository) {
        this.userLanguageRepository = userLanguageRepository;
        this.masterProfileRepository = masterProfileRepository;
    }

    @Transactional
    public UserLanguageResponse saveUserLanguage(UserLanguageRequest request) {
        UserLanguage userLanguage = new UserLanguage();
        userLanguage.setMasterProfile(getMasterProfile());
        applyRequest(userLanguage, request);

        return new UserLanguageResponse(
                userLanguageRepository.save(userLanguage));
    }

    @Transactional(readOnly = true)
    public List<UserLanguageResponse> getUserLanguages() {
        return userLanguageRepository
                .findByMasterProfileUserEmail(getAuthenticatedEmail())
                .stream()
                .map(UserLanguageResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserLanguageResponse getUserLanguage(Long id) {
        return new UserLanguageResponse(getOwnedUserLanguage(id));
    }

    @Transactional
    public UserLanguageResponse updateUserLanguage(
            Long id, UserLanguageRequest request) {
        UserLanguage existing = getOwnedUserLanguage(id);
        applyRequest(existing, request);

        return new UserLanguageResponse(
                userLanguageRepository.save(existing));
    }

    @Transactional
    public void deleteUserLanguage(Long id) {
        userLanguageRepository.delete(getOwnedUserLanguage(id));
    }

    private void applyRequest(
            UserLanguage userLanguage, UserLanguageRequest request) {
        userLanguage.setLanguage(request.getLanguage());
        userLanguage.setProficiencyLevel(request.getProficiencyLevel());
        userLanguage.setConfidence(request.getConfidence());
        userLanguage.setHasCertification(request.getHasCertification());
        userLanguage.setCertificationIssuer(request.getCertificationIssuer());
    }

    private UserLanguage getOwnedUserLanguage(Long id) {
        return userLanguageRepository
                .findByIdAndMasterProfileUserEmail(id, getAuthenticatedEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Idioma no encontrado"));
    }

    private MasterProfile getMasterProfile() {
        return masterProfileRepository.findByUserEmail(getAuthenticatedEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Perfil maestro no encontrado"));
    }

    private String getAuthenticatedEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}