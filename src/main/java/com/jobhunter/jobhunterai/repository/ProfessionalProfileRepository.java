
package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.ProfessionalProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalProfileRepository
        extends JpaRepository<ProfessionalProfile, Long> {

    List<ProfessionalProfile> findByMasterProfileUserEmail(String email);
}
