
package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.dto.ProfessionalProfileRequest;
import com.jobhunter.jobhunterai.dto.ProfessionalProfileResponse;
import com.jobhunter.jobhunterai.model.MasterProfile;
import com.jobhunter.jobhunterai.model.ProfessionalProfile;
import com.jobhunter.jobhunterai.repository.MasterProfileRepository;
import com.jobhunter.jobhunterai.repository.ProfessionalProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessionalProfileService {

    private final ProfessionalProfileRepository professionalProfileRepository;
    private final MasterProfileRepository masterProfileRepository;
    private final AuthorizationService authorizationService;

    public ProfessionalProfileService(
            ProfessionalProfileRepository professionalProfileRepository,
            MasterProfileRepository masterProfileRepository,
            AuthorizationService authorizationService) {
        this.professionalProfileRepository = professionalProfileRepository;
        this.masterProfileRepository = masterProfileRepository;
        this.authorizationService = authorizationService;
    }

    public ProfessionalProfileResponse saveProfessionalProfile(
            ProfessionalProfileRequest request) {

        MasterProfile masterProfile = getAuthenticatedMasterProfile();

        ProfessionalProfile profile = new ProfessionalProfile();
        profile.setMasterProfile(masterProfile);
        updateFields(profile, request);

        return toResponse(professionalProfileRepository.save(profile));
    }

    public List<ProfessionalProfileResponse> getProfessionalProfiles() {
        String email = getAuthenticatedEmail();

        return professionalProfileRepository
                .findByMasterProfileUserEmail(email)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProfessionalProfileResponse getProfessionalProfile(Long id) {
        return toResponse(findOwnedProfile(id));
    }

    public ProfessionalProfileResponse updateProfessionalProfile(
            Long id,
            ProfessionalProfileRequest request) {

        ProfessionalProfile profile = findOwnedProfile(id);
        updateFields(profile, request);

        return toResponse(professionalProfileRepository.save(profile));
    }

    public void deleteProfessionalProfile(Long id) {
        ProfessionalProfile profile = findOwnedProfile(id);
        professionalProfileRepository.delete(profile);
    }

    private ProfessionalProfile findOwnedProfile(Long id) {
        ProfessionalProfile profile = professionalProfileRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Perfil profesional no encontrado"));

        authorizationService.checkMasterProfileOwnership(
                profile.getMasterProfile());

        return profile;
    }

    private MasterProfile getAuthenticatedMasterProfile() {
        String email = getAuthenticatedEmail();

        return masterProfileRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Perfil maestro no encontrado"));
    }

    private String getAuthenticatedEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    private void updateFields(
            ProfessionalProfile profile,
            ProfessionalProfileRequest request) {
        profile.setName(request.getName());
        profile.setHeadline(request.getHeadline());
        profile.setProfessionalSummary(request.getProfessionalSummary());
    }

    private ProfessionalProfileResponse toResponse(
            ProfessionalProfile profile) {
        return new ProfessionalProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getHeadline(),
                profile.getProfessionalSummary());
    }
}
