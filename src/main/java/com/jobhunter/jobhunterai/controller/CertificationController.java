package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.Certification;
import com.jobhunter.jobhunterai.service.CertificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping
    public Certification createCertification(
            @RequestBody Certification certification) {
        return certificationService.saveCertification(certification);
    }

    @GetMapping
    public List<Certification> getCertifications() {
        return certificationService.getCertifications();
    }

    @GetMapping("/{id}")
    public Certification getCertification(@PathVariable Long id) {
        return certificationService.getCertification(id);
    }

    @PutMapping("/{id}")
    public Certification updateCertification(
            @PathVariable Long id,
            @RequestBody Certification certification) {
        return certificationService.updateCertification(id, certification);
    }

    @DeleteMapping("/{id}")
    public void deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
    }
}