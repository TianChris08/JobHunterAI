package com.jobhunter.jobhunterai.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_languages")
public class UserLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_profile_id", nullable = false)
    private MasterProfile masterProfile;

    private String language;

    private String proficiencyLevel;

    @Column
    private Integer confidence;

    private Boolean hasCertification;

    private String certificationIssuer;

    public UserLanguage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MasterProfile getMasterProfile() {
        return masterProfile;
    }

    public void setMasterProfile(MasterProfile masterProfile) {
        this.masterProfile = masterProfile;
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