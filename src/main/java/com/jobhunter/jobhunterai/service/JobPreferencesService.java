
package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.dto.JobPreferencesRequest;
import com.jobhunter.jobhunterai.dto.JobPreferencesResponse;
import com.jobhunter.jobhunterai.model.JobPreferences;
import com.jobhunter.jobhunterai.model.ProfessionalProfile;
import com.jobhunter.jobhunterai.repository.JobPreferencesRepository;
import com.jobhunter.jobhunterai.repository.ProfessionalProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobPreferencesService {

    private final JobPreferencesRepository jobPreferencesRepository;
    private final ProfessionalProfileRepository professionalProfileRepository;
    private final AuthorizationService authorizationService;

    public JobPreferencesService(
            JobPreferencesRepository jobPreferencesRepository,
            ProfessionalProfileRepository professionalProfileRepository,
            AuthorizationService authorizationService) {
        this.jobPreferencesRepository = jobPreferencesRepository;
        this.professionalProfileRepository = professionalProfileRepository;
        this.authorizationService = authorizationService;
    }

    public JobPreferencesResponse saveJobPreferences(
            JobPreferencesRequest request) {

        ProfessionalProfile profile = findOwnedProfessionalProfile(
                request.getProfessionalProfileId());

        JobPreferences preferences = new JobPreferences();
        preferences.setProfessionalProfile(profile);
        updateFields(preferences, request);

        return toResponse(jobPreferencesRepository.save(preferences));
    }

    public List<JobPreferencesResponse> getJobPreferences() {
        String email = getAuthenticatedEmail();

        return jobPreferencesRepository
                .findByProfessionalProfileMasterProfileUserEmail(email)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public JobPreferencesResponse getJobPreferences(Long id) {
        return toResponse(findOwnedPreferences(id));
    }

    public JobPreferencesResponse updateJobPreferences(
            Long id,
            JobPreferencesRequest request) {

        JobPreferences preferences = findOwnedPreferences(id);
        updateFields(preferences, request);

        return toResponse(jobPreferencesRepository.save(preferences));
    }

    public void deleteJobPreferences(Long id) {
        JobPreferences preferences = findOwnedPreferences(id);
        jobPreferencesRepository.delete(preferences);
    }

    private JobPreferences findOwnedPreferences(Long id) {
        JobPreferences preferences = jobPreferencesRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Preferencias laborales no encontradas"));

        authorizationService.checkMasterProfileOwnership(
                preferences.getProfessionalProfile().getMasterProfile());

        return preferences;
    }

    private ProfessionalProfile findOwnedProfessionalProfile(Long id) {
        if (id == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "professionalProfileId es obligatorio");
        }

        ProfessionalProfile profile = professionalProfileRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Perfil profesional no encontrado"));

        authorizationService.checkMasterProfileOwnership(
                profile.getMasterProfile());

        return profile;
    }

    private String getAuthenticatedEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    private void updateFields(
            JobPreferences preferences,
            JobPreferencesRequest request) {
        preferences.setRemotePreference(request.getRemotePreference());
        preferences.setRelocationPreference(request.getRelocationPreference());
        preferences.setMaximumDistanceKm(request.getMaximumDistanceKm());
        preferences.setEmploymentType(request.getEmploymentType());
        preferences.setMinimumSalary(request.getMinimumSalary());
        preferences.setSalaryCurrency(request.getSalaryCurrency());
        preferences.setPreferredIndustries(request.getPreferredIndustries());
    }

    private JobPreferencesResponse toResponse(JobPreferences preferences) {
        return new JobPreferencesResponse(
                preferences.getId(),
                preferences.getProfessionalProfile().getId(),
                preferences.getRemotePreference(),
                preferences.getRelocationPreference(),
                preferences.getMaximumDistanceKm(),
                preferences.getEmploymentType(),
                preferences.getMinimumSalary(),
                preferences.getSalaryCurrency(),
                preferences.getPreferredIndustries());
    }
}
