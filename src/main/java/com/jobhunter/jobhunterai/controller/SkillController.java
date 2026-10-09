package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.dto.SkillRequest;
import com.jobhunter.jobhunterai.dto.SkillResponse;
import com.jobhunter.jobhunterai.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillResponse createSkill(@RequestBody SkillRequest request) {
        return skillService.saveSkill(request);
    }

    @GetMapping
    public List<SkillResponse> getSkills() {
        return skillService.getSkills();
    }

    @GetMapping("/{id}")
    public SkillResponse getSkill(@PathVariable Long id) {
        return skillService.getSkill(id);
    }
}