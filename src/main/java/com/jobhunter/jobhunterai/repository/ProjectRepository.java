package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}