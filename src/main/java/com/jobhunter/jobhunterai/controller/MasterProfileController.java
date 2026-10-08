package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.MasterProfile;
import com.jobhunter.jobhunterai.service.MasterProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master-profiles")
public class MasterProfileController {

    private final MasterProfileService masterProfileService;

    public MasterProfileController(MasterProfileService masterProfileService) {
        this.masterProfileService = masterProfileService;
    }

    @PostMapping
    public MasterProfile createMasterProfile(@RequestBody MasterProfile masterProfile) {
        return masterProfileService.saveMasterProfile(masterProfile);
    }

    @GetMapping
    public List<MasterProfile> getMasterProfiles() {
        return masterProfileService.getMasterProfiles();
    }

    @GetMapping("/{id}")
    public MasterProfile getMasterProfile(@PathVariable Long id) {
        return masterProfileService.getMasterProfile(id);
    }

    @PutMapping("/{id}")
    public MasterProfile updateMasterProfile(
            @PathVariable Long id,
            @RequestBody MasterProfile masterProfile) {

        return masterProfileService.updateMasterProfile(id, masterProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteMasterProfile(@PathVariable Long id) {
        masterProfileService.deleteMasterProfile(id);
    }
}