package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.MasterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterProfileRepository extends JpaRepository<MasterProfile, Long> {

    Optional<MasterProfile> findByUserEmail(String email);

}