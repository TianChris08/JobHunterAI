package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.Experience;
import com.jobhunter.jobhunterai.model.MasterProfile;
import com.jobhunter.jobhunterai.repository.ExperienceRepository;
import com.jobhunter.jobhunterai.repository.MasterProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final AuthorizationService authorizationService;
    private final MasterProfileRepository masterProfileRepository;

    public ExperienceService(ExperienceRepository experienceRepository,
                             AuthorizationService authorizationService,
                             MasterProfileRepository masterProfileRepository) {
        this.experienceRepository = experienceRepository;
        this.authorizationService = authorizationService;
        this.masterProfileRepository = masterProfileRepository;
    }

    public Experience saveExperience(Experience experience) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String authenticatedEmail = authentication.getName();

        MasterProfile masterProfile =
                masterProfileRepository.findByUserEmail(authenticatedEmail)
                        .orElseThrow();

        experience.setMasterProfile(masterProfile);

        return experienceRepository.save(experience);
    }

    public List<Experience> getExperiences() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String authenticatedEmail = authentication.getName();

        return experienceRepository.findByMasterProfileUserEmail(
                authenticatedEmail
        );
    }

    public Experience getExperience(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow();

        authorizationService.checkMasterProfileOwnership(
                experience.getMasterProfile()
        );

        return experience;
    }

    public Experience updateExperience(Long id, Experience experience) {
        Experience existingExperience = getExperience(id);

        existingExperience.setCompanyName(experience.getCompanyName());
        existingExperience.setPosition(experience.getPosition());
        existingExperience.setType(experience.getType());
        existingExperience.setStartDate(experience.getStartDate());
        existingExperience.setEndDate(experience.getEndDate());
        existingExperience.setCurrentlyWorking(experience.getCurrentlyWorking());
        existingExperience.setDescription(experience.getDescription());
        existingExperience.setResponsibilities(experience.getResponsibilities());
        existingExperience.setAchievements(experience.getAchievements());
        existingExperience.setUserNotes(experience.getUserNotes());
        existingExperience.setVisibility(experience.getVisibility());

        return experienceRepository.save(existingExperience);
    }

    public void deleteExperience(Long id) {
        Experience experience = getExperience(id);
        experienceRepository.delete(experience);
    }
}