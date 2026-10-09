package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.UserSkill;

public class UserSkillResponse {

    private Long id;
    private Long skillId;
    private String skillName;

    public UserSkillResponse(UserSkill userSkill) {
        this.id = userSkill.getId();
        this.skillId = userSkill.getSkill().getId();
        this.skillName = userSkill.getSkill().getName();
    }

    public Long getId() {
        return id;
    }

    public Long getSkillId() {
        return skillId;
    }

    public String getSkillName() {
        return skillName;
    }
}