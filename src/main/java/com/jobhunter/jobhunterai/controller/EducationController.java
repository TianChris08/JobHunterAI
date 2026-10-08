package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.Education;
import com.jobhunter.jobhunterai.service.EducationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    public Education createEducation(@RequestBody Education education) {
        return educationService.saveEducation(education);
    }

    @GetMapping
    public List<Education> getEducations() {
        return educationService.getEducations();
    }

    @GetMapping("/{id}")
    public Education getEducation(@PathVariable Long id) {
        return educationService.getEducation(id);
    }

    @PutMapping("/{id}")
    public Education updateEducation(
            @PathVariable Long id,
            @RequestBody Education education) {
        return educationService.updateEducation(id, education);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
    }
}