package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.Education;
import java.time.LocalDate;

public class EducationResponse {

    private Long id;
    private String title;
    private String institution;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public EducationResponse(Education education) {
        this.id = education.getId();
        this.title = education.getTitle();
        this.institution = education.getInstitution();
        this.startDate = education.getStartDate();
        this.endDate = education.getEndDate();
        this.status = education.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInstitution() {
        return institution;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }
}