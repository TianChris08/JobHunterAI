package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.JobPreferences;
import com.jobhunter.jobhunterai.repository.JobPreferencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPreferencesService {

    private final JobPreferencesRepository jobPreferencesRepository;

    public JobPreferencesService(JobPreferencesRepository jobPreferencesRepository) {
        this.jobPreferencesRepository = jobPreferencesRepository;
    }

    public JobPreferences saveJobPreferences(JobPreferences jobPreferences) {
        return jobPreferencesRepository.save(jobPreferences);
    }

    public List<JobPreferences> getJobPreferences() {
        return jobPreferencesRepository.findAll();
    }

    public JobPreferences getJobPreferences(Long id) {
        return jobPreferencesRepository.findById(id)
                .orElseThrow();
    }

    public JobPreferences updateJobPreferences(Long id, JobPreferences jobPreferences) {
        JobPreferences existingPreferences = getJobPreferences(id);

        existingPreferences.setProfessionalProfile(jobPreferences.getProfessionalProfile());
        existingPreferences.setRemotePreference(jobPreferences.getRemotePreference());
        existingPreferences.setRelocationPreference(jobPreferences.getRelocationPreference());
        existingPreferences.setMaximumDistanceKm(jobPreferences.getMaximumDistanceKm());
        existingPreferences.setEmploymentType(jobPreferences.getEmploymentType());
        existingPreferences.setMinimumSalary(jobPreferences.getMinimumSalary());
        existingPreferences.setSalaryCurrency(jobPreferences.getSalaryCurrency());
        existingPreferences.setPreferredIndustries(jobPreferences.getPreferredIndustries());

        return jobPreferencesRepository.save(existingPreferences);
    }

    public void deleteJobPreferences(Long id) {
        jobPreferencesRepository.deleteById(id);
    }
}