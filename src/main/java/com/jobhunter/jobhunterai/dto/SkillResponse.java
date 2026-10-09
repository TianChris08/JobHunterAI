package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.Skill;

public class SkillResponse {

    private Long id;
    private String name;

    public SkillResponse(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}