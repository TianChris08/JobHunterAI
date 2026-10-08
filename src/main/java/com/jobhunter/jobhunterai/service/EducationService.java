package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.Education;
import com.jobhunter.jobhunterai.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    public List<Education> getEducations() {
        return educationRepository.findAll();
    }

    public Education getEducation(Long id) {
        return educationRepository.findById(id)
                .orElseThrow();
    }

    public Education updateEducation(Long id, Education education) {
        Education existingEducation = getEducation(id);

        existingEducation.setMasterProfile(education.getMasterProfile());
        existingEducation.setTitle(education.getTitle());
        existingEducation.setInstitution(education.getInstitution());
        existingEducation.setStartDate(education.getStartDate());
        existingEducation.setEndDate(education.getEndDate());
        existingEducation.setStatus(education.getStatus());

        return educationRepository.save(existingEducation);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}