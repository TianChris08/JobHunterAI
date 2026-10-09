package com.jobhunter.jobhunterai.dto;

public class UserLanguageRequest {

    private String language;
    private String proficiencyLevel;
    private Integer confidence;
    private Boolean hasCertification;
    private String certificationIssuer;

    public UserLanguageRequest() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Boolean getHasCertification() {
        return hasCertification;
    }

    public void setHasCertification(Boolean hasCertification) {
        this.hasCertification = hasCertification;
    }

    public String getCertificationIssuer() {
        return certificationIssuer;
    }

    public void setCertificationIssuer(String certificationIssuer) {
        this.certificationIssuer = certificationIssuer;
    }
}