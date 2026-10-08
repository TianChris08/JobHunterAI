package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.Experience;

import java.time.LocalDate;

public class ExperienceResponse {

    private Long id;
    private String companyName;
    private String position;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean currentlyWorking;
    private String description;
    private String responsibilities;
    private String achievements;
    private String userNotes;
    private Boolean visibility;

    public ExperienceResponse(Experience experience) {
        this.id = experience.getId();
        this.companyName = experience.getCompanyName();
        this.position = experience.getPosition();
        this.type = experience.getType();
        this.startDate = experience.getStartDate();
        this.endDate = experience.getEndDate();
        this.currentlyWorking = experience.getCurrentlyWorking();
        this.description = experience.getDescription();
        this.responsibilities = experience.getResponsibilities();
        this.achievements = experience.getAchievements();
        this.userNotes = experience.getUserNotes();
        this.visibility = experience.getVisibility();
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Boolean getCurrentlyWorking() {
        return currentlyWorking;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public String getAchievements() {
        return achievements;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public Boolean getVisibility() {
        return visibility;
    }
}