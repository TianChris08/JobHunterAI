package com.jobhunter.jobhunterai.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "job_preferences")
public class JobPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "professional_profile_id", nullable = false, unique = true)
    private ProfessionalProfile professionalProfile;

    private String remotePreference;

    private String relocationPreference;

    private Integer maximumDistanceKm;

    private String employmentType;

    private BigDecimal minimumSalary;

    @Column(length = 3)
    private String salaryCurrency;

    @Column(columnDefinition = "TEXT")
    private String preferredIndustries;

    public JobPreferences() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfessionalProfile getProfessionalProfile() {
        return professionalProfile;
    }

    public void setProfessionalProfile(ProfessionalProfile professionalProfile) {
        this.professionalProfile = professionalProfile;
    }

    public String getRemotePreference() {
        return remotePreference;
    }

    public void setRemotePreference(String remotePreference) {
        this.remotePreference = remotePreference;
    }

    public String getRelocationPreference() {
        return relocationPreference;
    }

    public void setRelocationPreference(String relocationPreference) {
        this.relocationPreference = relocationPreference;
    }

    public Integer getMaximumDistanceKm() {
        return maximumDistanceKm;
    }

    public void setMaximumDistanceKm(Integer maximumDistanceKm) {
        this.maximumDistanceKm = maximumDistanceKm;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public BigDecimal getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(BigDecimal minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public String getPreferredIndustries() {
        return preferredIndustries;
    }

    public void setPreferredIndustries(String preferredIndustries) {
        this.preferredIndustries = preferredIndustries;
    }
}