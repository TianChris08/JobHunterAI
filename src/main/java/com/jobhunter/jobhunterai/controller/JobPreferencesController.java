package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.JobPreferences;
import com.jobhunter.jobhunterai.service.JobPreferencesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-preferences")
public class JobPreferencesController {

    private final JobPreferencesService jobPreferencesService;

    public JobPreferencesController(JobPreferencesService jobPreferencesService) {
        this.jobPreferencesService = jobPreferencesService;
    }

    @PostMapping
    public JobPreferences createJobPreferences(@RequestBody JobPreferences jobPreferences) {
        return jobPreferencesService.saveJobPreferences(jobPreferences);
    }

    @GetMapping
    public List<JobPreferences> getJobPreferences() {
        return jobPreferencesService.getJobPreferences();
    }

    @GetMapping("/{id}")
    public JobPreferences getJobPreferences(@PathVariable Long id) {
        return jobPreferencesService.getJobPreferences(id);
    }

    @PutMapping("/{id}")
    public JobPreferences updateJobPreferences(
            @PathVariable Long id,
            @RequestBody JobPreferences jobPreferences) {
        return jobPreferencesService.updateJobPreferences(id, jobPreferences);
    }

    @DeleteMapping("/{id}")
    public void deleteJobPreferences(@PathVariable Long id) {
        jobPreferencesService.deleteJobPreferences(id);
    }
}