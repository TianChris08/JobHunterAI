
package com.jobhunter.jobhunterai.dto;

import java.math.BigDecimal;

public class JobPreferencesRequest {

    private Long professionalProfileId;
    private String remotePreference;
    private String relocationPreference;
    private Integer maximumDistanceKm;
    private String employmentType;
    private BigDecimal minimumSalary;
    private String salaryCurrency;
    private String preferredIndustries;

    public JobPreferencesRequest() {
    }

    public Long getProfessionalProfileId() {
        return professionalProfileId;
    }

    public void setProfessionalProfileId(Long professionalProfileId) {
        this.professionalProfileId = professionalProfileId;
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
