
package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.JobPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPreferencesRepository
        extends JpaRepository<JobPreferences, Long> {

    List<JobPreferences> findByProfessionalProfileMasterProfileUserEmail(String email);
}
