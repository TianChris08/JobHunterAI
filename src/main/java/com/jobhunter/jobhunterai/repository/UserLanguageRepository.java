package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLanguageRepository
        extends JpaRepository<UserLanguage, Long> {

    List<UserLanguage> findByMasterProfileUserEmail(String email);

    Optional<UserLanguage> findByIdAndMasterProfileUserEmail(
            Long id, String email);
}