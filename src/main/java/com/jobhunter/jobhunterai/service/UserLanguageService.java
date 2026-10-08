package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.UserLanguage;
import com.jobhunter.jobhunterai.repository.UserLanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLanguageService {

    private final UserLanguageRepository userLanguageRepository;

    public UserLanguageService(UserLanguageRepository userLanguageRepository) {
        this.userLanguageRepository = userLanguageRepository;
    }

    public UserLanguage saveUserLanguage(UserLanguage userLanguage) {
        return userLanguageRepository.save(userLanguage);
    }

    public List<UserLanguage> getUserLanguages() {
        return userLanguageRepository.findAll();
    }

    public UserLanguage getUserLanguage(Long id) {
        return userLanguageRepository.findById(id)
                .orElseThrow();
    }

    public UserLanguage updateUserLanguage(Long id, UserLanguage userLanguage) {
        UserLanguage existingLanguage = getUserLanguage(id);

        existingLanguage.setMasterProfile(userLanguage.getMasterProfile());
        existingLanguage.setLanguage(userLanguage.getLanguage());
        existingLanguage.setProficiencyLevel(userLanguage.getProficiencyLevel());
        existingLanguage.setConfidence(userLanguage.getConfidence());
        existingLanguage.setHasCertification(userLanguage.getHasCertification());
        existingLanguage.setCertificationIssuer(userLanguage.getCertificationIssuer());

        return userLanguageRepository.save(existingLanguage);
    }

    public void deleteUserLanguage(Long id) {
        userLanguageRepository.deleteById(id);
    }
}