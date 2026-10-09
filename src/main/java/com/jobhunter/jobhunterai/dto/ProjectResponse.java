package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.Project;
import java.time.LocalDate;

public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String role;
    private String achievements;
    private String repositoryUrl;
    private String websiteUrl;
    private Integer aiUsagePercentage;
    private String aiUsageDescription;

    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.role = project.getRole();
        this.achievements = project.getAchievements();
        this.repositoryUrl = project.getRepositoryUrl();
        this.websiteUrl = project.getWebsiteUrl();
        this.aiUsagePercentage = project.getAiUsagePercentage();
        this.aiUsageDescription = project.getAiUsageDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getRole() {
        return role;
    }

    public String getAchievements() {
        return achievements;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public Integer getAiUsagePercentage() {
        return aiUsagePercentage;
    }

    public String getAiUsageDescription() {
        return aiUsageDescription;
    }
}