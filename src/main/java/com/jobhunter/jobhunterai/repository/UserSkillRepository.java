package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

    List<UserSkill> findByMasterProfileUserEmail(String email);

    Optional<UserSkill> findByIdAndMasterProfileUserEmail(Long id, String email);

    boolean existsByMasterProfileIdAndSkillId(Long masterProfileId, Long skillId);
}