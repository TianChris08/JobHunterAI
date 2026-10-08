package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.ProfessionalProfile;
import com.jobhunter.jobhunterai.repository.ProfessionalProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalProfileService {

    private final ProfessionalProfileRepository professionalProfileRepository;

    public ProfessionalProfileService(ProfessionalProfileRepository professionalProfileRepository) {
        this.professionalProfileRepository = professionalProfileRepository;
    }

    public ProfessionalProfile saveProfessionalProfile(ProfessionalProfile professionalProfile) {
        return professionalProfileRepository.save(professionalProfile);
    }

    public List<ProfessionalProfile> getProfessionalProfiles() {
        return professionalProfileRepository.findAll();
    }

    public ProfessionalProfile getProfessionalProfile(Long id) {
        return professionalProfileRepository.findById(id)
                .orElseThrow();
    }

    public ProfessionalProfile updateProfessionalProfile(Long id, ProfessionalProfile professionalProfile) {
        ProfessionalProfile existingProfile = getProfessionalProfile(id);

        existingProfile.setMasterProfile(professionalProfile.getMasterProfile());
        existingProfile.setName(professionalProfile.getName());
        existingProfile.setHeadline(professionalProfile.getHeadline());
        existingProfile.setProfessionalSummary(professionalProfile.getProfessionalSummary());

        return professionalProfileRepository.save(existingProfile);
    }

    public void deleteProfessionalProfile(Long id) {
        professionalProfileRepository.deleteById(id);
    }
}