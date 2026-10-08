package com.jobhunter.jobhunterai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({
            "/",
            "/chat",
            "/experience",
            "/education",
            "/skills",
            "/languages",
            "/certifications",
            "/courses",
            "/projects"
    })
    public String app() {
        return "index";
    }
}