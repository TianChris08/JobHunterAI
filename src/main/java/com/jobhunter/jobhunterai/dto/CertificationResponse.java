package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.Certification;
import java.time.LocalDate;

public class CertificationResponse {

    private Long id;
    private String name;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String credentialId;
    private String credentialUrl;

    public CertificationResponse(Certification certification) {
        this.id = certification.getId();
        this.name = certification.getName();
        this.issuer = certification.getIssuer();
        this.issueDate = certification.getIssueDate();
        this.expirationDate = certification.getExpirationDate();
        this.credentialId = certification.getCredentialId();
        this.credentialUrl = certification.getCredentialUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIssuer() {
        return issuer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }
}