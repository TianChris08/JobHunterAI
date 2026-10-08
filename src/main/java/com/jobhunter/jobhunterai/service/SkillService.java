package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.Skill;
import com.jobhunter.jobhunterai.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkill(Long id) {
        return skillRepository.findById(id)
                .orElseThrow();
    }

    public Skill updateSkill(Long id, Skill skill) {
        Skill existingSkill = getSkill(id);

        existingSkill.setName(skill.getName());
        existingSkill.setNormalizedName(skill.getNormalizedName());

        return skillRepository.save(existingSkill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}