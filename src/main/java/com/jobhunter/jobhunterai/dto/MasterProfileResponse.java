package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.MasterProfile;

public class MasterProfileResponse {

    private Long id;
    private String headline;
    private String professionalSummary;
    private String country;
    private String city;
    private String postalCode;
    private String fullAddress;

    public MasterProfileResponse(MasterProfile masterProfile) {
        this.id = masterProfile.getId();
        this.headline = masterProfile.getHeadline();
        this.professionalSummary = masterProfile.getProfessionalSummary();
        this.country = masterProfile.getCountry();
        this.city = masterProfile.getCity();
        this.postalCode = masterProfile.getPostalCode();
        this.fullAddress = masterProfile.getFullAddress();
    }

    public Long getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }
}