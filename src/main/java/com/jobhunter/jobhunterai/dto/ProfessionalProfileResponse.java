
package com.jobhunter.jobhunterai.dto;

public class ProfessionalProfileResponse {

    private Long id;
    private String name;
    private String headline;
    private String professionalSummary;

    public ProfessionalProfileResponse() {
    }

    public ProfessionalProfileResponse(
            Long id,
            String name,
            String headline,
            String professionalSummary) {
        this.id = id;
        this.name = name;
        this.headline = headline;
        this.professionalSummary = professionalSummary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }
}
