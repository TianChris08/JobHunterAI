package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.UserLanguage;

public class UserLanguageResponse {

    private Long id;
    private String language;
    private String proficiencyLevel;
    private Integer confidence;
    private Boolean hasCertification;
    private String certificationIssuer;

    public UserLanguageResponse(UserLanguage userLanguage) {
        this.id = userLanguage.getId();
        this.language = userLanguage.getLanguage();
        this.proficiencyLevel = userLanguage.getProficiencyLevel();
        this.confidence = userLanguage.getConfidence();
        this.hasCertification = userLanguage.getHasCertification();
        this.certificationIssuer = userLanguage.getCertificationIssuer();
    }

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public Boolean getHasCertification() {
        return hasCertification;
    }

    public String getCertificationIssuer() {
        return certificationIssuer;
    }
}