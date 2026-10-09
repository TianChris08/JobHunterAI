
package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.dto.ProfessionalProfileRequest;
import com.jobhunter.jobhunterai.dto.ProfessionalProfileResponse;
import com.jobhunter.jobhunterai.service.ProfessionalProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professional-profiles")
public class ProfessionalProfileController {

    private final ProfessionalProfileService professionalProfileService;

    public ProfessionalProfileController(
            ProfessionalProfileService professionalProfileService) {
        this.professionalProfileService = professionalProfileService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionalProfileResponse createProfessionalProfile(
            @RequestBody ProfessionalProfileRequest request) {
        return professionalProfileService.saveProfessionalProfile(request);
    }

    @GetMapping
    public List<ProfessionalProfileResponse> getProfessionalProfiles() {
        return professionalProfileService.getProfessionalProfiles();
    }

    @GetMapping("/{id}")
    public ProfessionalProfileResponse getProfessionalProfile(
            @PathVariable Long id) {
        return professionalProfileService.getProfessionalProfile(id);
    }

    @PutMapping("/{id}")
    public ProfessionalProfileResponse updateProfessionalProfile(
            @PathVariable Long id,
            @RequestBody ProfessionalProfileRequest request) {
        return professionalProfileService.updateProfessionalProfile(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessionalProfile(@PathVariable Long id) {
        professionalProfileService.deleteProfessionalProfile(id);
    }
}
