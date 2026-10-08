package com.jobhunter.jobhunterai.dto;

import com.jobhunter.jobhunterai.model.User;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private boolean emailVerified;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.emailVerified = user.isEmailVerified();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }
}