package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.Certification;
import com.jobhunter.jobhunterai.repository.CertificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public Certification saveCertification(Certification certification) {
        return certificationRepository.save(certification);
    }

    public List<Certification> getCertifications() {
        return certificationRepository.findAll();
    }

    public Certification getCertification(Long id) {
        return certificationRepository.findById(id)
                .orElseThrow();
    }

    public Certification updateCertification(Long id, Certification certification) {
        Certification existingCertification = getCertification(id);

        existingCertification.setMasterProfile(certification.getMasterProfile());
        existingCertification.setName(certification.getName());
        existingCertification.setIssuer(certification.getIssuer());
        existingCertification.setIssueDate(certification.getIssueDate());
        existingCertification.setExpirationDate(certification.getExpirationDate());
        existingCertification.setCredentialId(certification.getCredentialId());
        existingCertification.setCredentialUrl(certification.getCredentialUrl());

        return certificationRepository.save(existingCertification);
    }

    public void deleteCertification(Long id) {
        certificationRepository.deleteById(id);
    }
}