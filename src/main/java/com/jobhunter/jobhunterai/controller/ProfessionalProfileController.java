package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.ProfessionalProfile;
import com.jobhunter.jobhunterai.service.ProfessionalProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professional-profiles")
public class ProfessionalProfileController {

    private final ProfessionalProfileService professionalProfileService;

    public ProfessionalProfileController(ProfessionalProfileService professionalProfileService) {
        this.professionalProfileService = professionalProfileService;
    }

    @PostMapping
    public ProfessionalProfile createProfessionalProfile(
            @RequestBody ProfessionalProfile professionalProfile) {
        return professionalProfileService.saveProfessionalProfile(professionalProfile);
    }

    @GetMapping
    public List<ProfessionalProfile> getProfessionalProfiles() {
        return professionalProfileService.getProfessionalProfiles();
    }

    @GetMapping("/{id}")
    public ProfessionalProfile getProfessionalProfile(@PathVariable Long id) {
        return professionalProfileService.getProfessionalProfile(id);
    }

    @PutMapping("/{id}")
    public ProfessionalProfile updateProfessionalProfile(
            @PathVariable Long id,
            @RequestBody ProfessionalProfile professionalProfile) {
        return professionalProfileService.updateProfessionalProfile(id, professionalProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessionalProfile(@PathVariable Long id) {
        professionalProfileService.deleteProfessionalProfile(id);
    }
}