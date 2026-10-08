package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.JobPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPreferencesRepository extends JpaRepository<JobPreferences, Long> {
}