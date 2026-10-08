package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.UserSkill;
import com.jobhunter.jobhunterai.service.UserSkillService;
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
    public UserSkill createUserSkill(@RequestBody UserSkill userSkill) {
        return userSkillService.saveUserSkill(userSkill);
    }

    @GetMapping
    public List<UserSkill> getUserSkills() {
        return userSkillService.getUserSkills();
    }

    @GetMapping("/{id}")
    public UserSkill getUserSkill(@PathVariable Long id) {
        return userSkillService.getUserSkill(id);
    }

    @PutMapping("/{id}")
    public UserSkill updateUserSkill(
            @PathVariable Long id,
            @RequestBody UserSkill userSkill) {
        return userSkillService.updateUserSkill(id, userSkill);
    }

    @DeleteMapping("/{id}")
    public void deleteUserSkill(@PathVariable Long id) {
        userSkillService.deleteUserSkill(id);
    }
}