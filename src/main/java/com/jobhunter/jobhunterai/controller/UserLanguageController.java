package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.model.UserLanguage;
import com.jobhunter.jobhunterai.service.UserLanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-languages")
public class UserLanguageController {

    private final UserLanguageService userLanguageService;

    public UserLanguageController(UserLanguageService userLanguageService) {
        this.userLanguageService = userLanguageService;
    }

    @PostMapping
    public UserLanguage createUserLanguage(@RequestBody UserLanguage userLanguage) {
        return userLanguageService.saveUserLanguage(userLanguage);
    }

    @GetMapping
    public List<UserLanguage> getUserLanguages() {
        return userLanguageService.getUserLanguages();
    }

    @GetMapping("/{id}")
    public UserLanguage getUserLanguage(@PathVariable Long id) {
        return userLanguageService.getUserLanguage(id);
    }

    @PutMapping("/{id}")
    public UserLanguage updateUserLanguage(
            @PathVariable Long id,
            @RequestBody UserLanguage userLanguage) {
        return userLanguageService.updateUserLanguage(id, userLanguage);
    }

    @DeleteMapping("/{id}")
    public void deleteUserLanguage(@PathVariable Long id) {
        userLanguageService.deleteUserLanguage(id);
    }
}