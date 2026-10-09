package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.dto.UserSkillRequest;
import com.jobhunter.jobhunterai.dto.UserSkillResponse;
import com.jobhunter.jobhunterai.service.UserSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-skills")
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserSkillResponse createUserSkill(
            @RequestBody UserSkillRequest request) {
        return userSkillService.saveUserSkill(request);
    }

    @GetMapping
    public List<UserSkillResponse> getUserSkills() {
        return userSkillService.getUserSkills();
    }

    @GetMapping("/{id}")
    public UserSkillResponse getUserSkill(@PathVariable Long id) {
        return userSkillService.getUserSkill(id);
    }

    @PutMapping("/{id}")
    public UserSkillResponse updateUserSkill(
            @PathVariable Long id,
            @RequestBody UserSkillRequest request) {
        return userSkillService.updateUserSkill(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserSkill(@PathVariable Long id) {
        userSkillService.deleteUserSkill(id);
    }
}