package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findByMasterProfileUserEmail(String email);

}