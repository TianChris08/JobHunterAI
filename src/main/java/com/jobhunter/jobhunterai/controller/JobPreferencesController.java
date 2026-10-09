
package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.dto.JobPreferencesRequest;
import com.jobhunter.jobhunterai.dto.JobPreferencesResponse;
import com.jobhunter.jobhunterai.service.JobPreferencesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-preferences")
public class JobPreferencesController {

    private final JobPreferencesService jobPreferencesService;

    public JobPreferencesController(
            JobPreferencesService jobPreferencesService) {
        this.jobPreferencesService = jobPreferencesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobPreferencesResponse createJobPreferences(
            @RequestBody JobPreferencesRequest request) {
        return jobPreferencesService.saveJobPreferences(request);
    }

    @GetMapping
    public List<JobPreferencesResponse> getJobPreferences() {
        return jobPreferencesService.getJobPreferences();
    }

    @GetMapping("/{id}")
    public JobPreferencesResponse getJobPreferences(@PathVariable Long id) {
        return jobPreferencesService.getJobPreferences(id);
    }

    @PutMapping("/{id}")
    public JobPreferencesResponse updateJobPreferences(
            @PathVariable Long id,
            @RequestBody JobPreferencesRequest request) {
        return jobPreferencesService.updateJobPreferences(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJobPreferences(@PathVariable Long id) {
        jobPreferencesService.deleteJobPreferences(id);
    }
}
