package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}