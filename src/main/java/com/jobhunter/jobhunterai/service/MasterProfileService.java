package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.MasterProfile;
import com.jobhunter.jobhunterai.repository.MasterProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterProfileService {

    private final MasterProfileRepository masterProfileRepository;

    public MasterProfileService(MasterProfileRepository masterProfileRepository) {
        this.masterProfileRepository = masterProfileRepository;
    }

    public MasterProfile saveMasterProfile(MasterProfile masterProfile) {
        return masterProfileRepository.save(masterProfile);
    }

    public List<MasterProfile> getMasterProfiles() {
        return masterProfileRepository.findAll();
    }

    public MasterProfile getMasterProfile(Long id) {
        return masterProfileRepository.findById(id)
                .orElseThrow();
    }

    public MasterProfile updateMasterProfile(Long id, MasterProfile masterProfile) {
        MasterProfile existingProfile = getMasterProfile(id);

        existingProfile.setHeadline(masterProfile.getHeadline());
        existingProfile.setProfessionalSummary(masterProfile.getProfessionalSummary());
        existingProfile.setCountry(masterProfile.getCountry());
        existingProfile.setCity(masterProfile.getCity());
        existingProfile.setPostalCode(masterProfile.getPostalCode());
        existingProfile.setFullAddress(masterProfile.getFullAddress());

        return masterProfileRepository.save(existingProfile);
    }

    public void deleteMasterProfile(Long id) {
        masterProfileRepository.deleteById(id);
    }
}