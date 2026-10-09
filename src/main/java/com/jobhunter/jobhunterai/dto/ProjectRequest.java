package com.jobhunter.jobhunterai.dto;

import java.time.LocalDate;

public class ProjectRequest {

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

    public ProjectRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Integer getAiUsagePercentage() {
        return aiUsagePercentage;
    }

    public void setAiUsagePercentage(Integer aiUsagePercentage) {
        this.aiUsagePercentage = aiUsagePercentage;
    }

    public String getAiUsageDescription() {
        return aiUsageDescription;
    }

    public void setAiUsageDescription(String aiUsageDescription) {
        this.aiUsageDescription = aiUsageDescription;
    }
}