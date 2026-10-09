package com.jobhunter.jobhunterai.controller;

import com.jobhunter.jobhunterai.dto.UserLanguageRequest;
import com.jobhunter.jobhunterai.dto.UserLanguageResponse;
import com.jobhunter.jobhunterai.service.UserLanguageService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public UserLanguageResponse createUserLanguage(
            @RequestBody UserLanguageRequest request) {
        return userLanguageService.saveUserLanguage(request);
    }

    @GetMapping
    public List<UserLanguageResponse> getUserLanguages() {
        return userLanguageService.getUserLanguages();
    }

    @GetMapping("/{id}")
    public UserLanguageResponse getUserLanguage(@PathVariable Long id) {
        return userLanguageService.getUserLanguage(id);
    }

    @PutMapping("/{id}")
    public UserLanguageResponse updateUserLanguage(
            @PathVariable Long id,
            @RequestBody UserLanguageRequest request) {
        return userLanguageService.updateUserLanguage(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserLanguage(@PathVariable Long id) {
        userLanguageService.deleteUserLanguage(id);
    }
}