package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.Skill;
import com.jobhunter.jobhunterai.service.SkillService;
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
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.saveSkill(skill);
    }

    @GetMapping
    public List<Skill> getSkills() {
        return skillService.getSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillService.getSkill(id);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(
            @PathVariable Long id,
            @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}