package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.Project;
import com.jobhunter.jobhunterai.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow();
    }

    public Project updateProject(Long id, Project project) {
        Project existingProject = getProject(id);

        existingProject.setMasterProfile(project.getMasterProfile());
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setRole(project.getRole());
        existingProject.setAchievements(project.getAchievements());
        existingProject.setRepositoryUrl(project.getRepositoryUrl());
        existingProject.setWebsiteUrl(project.getWebsiteUrl());
        existingProject.setAiUsagePercentage(project.getAiUsagePercentage());
        existingProject.setAiUsageDescription(project.getAiUsageDescription());

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}