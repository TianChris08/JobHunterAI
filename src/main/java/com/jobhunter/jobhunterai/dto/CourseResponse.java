package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.Course;
import java.time.LocalDate;

public class CourseResponse {

    private Long id;
    private String name;
    private String provider;
    private String duration;
    private LocalDate startDate;
    private LocalDate completionDate;
    private String credentialUrl;

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.provider = course.getProvider();
        this.duration = course.getDuration();
        this.startDate = course.getStartDate();
        this.completionDate = course.getCompletionDate();
        this.credentialUrl = course.getCredentialUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProvider() {
        return provider;
    }

    public String getDuration() {
        return duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }
}