package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}