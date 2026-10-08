package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.dto.ExperienceRequest;
import com.jobhunter.jobhunterai.dto.ExperienceResponse;
import com.jobhunter.jobhunterai.model.Experience;
import com.jobhunter.jobhunterai.service.ExperienceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public ExperienceResponse createExperience(
            @RequestBody ExperienceRequest request) {

        Experience experience = new Experience();

        experience.setCompanyName(request.getCompanyName());
        experience.setPosition(request.getPosition());
        experience.setType(request.getType());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setCurrentlyWorking(request.getCurrentlyWorking());
        experience.setDescription(request.getDescription());
        experience.setResponsibilities(request.getResponsibilities());
        experience.setAchievements(request.getAchievements());
        experience.setUserNotes(request.getUserNotes());
        experience.setVisibility(request.getVisibility());

        return new ExperienceResponse(experienceService.saveExperience(experience));
    }

    @GetMapping
    public List<ExperienceResponse> getExperiences() {
        return experienceService.getExperiences()
                .stream()
                .map(ExperienceResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ExperienceResponse getExperience(@PathVariable Long id) {
        Experience experience = experienceService.getExperience(id);
        return new ExperienceResponse(experience);
    }

    @PutMapping("/{id}")
    public ExperienceResponse updateExperience(
            @PathVariable Long id,
            @RequestBody ExperienceRequest request) {

        Experience experience = new Experience();

        experience.setCompanyName(request.getCompanyName());
        experience.setPosition(request.getPosition());
        experience.setType(request.getType());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setCurrentlyWorking(request.getCurrentlyWorking());
        experience.setDescription(request.getDescription());
        experience.setResponsibilities(request.getResponsibilities());
        experience.setAchievements(request.getAchievements());
        experience.setUserNotes(request.getUserNotes());
        experience.setVisibility(request.getVisibility());

        return new ExperienceResponse(experienceService.updateExperience(id, experience));
    }

    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
    }
}