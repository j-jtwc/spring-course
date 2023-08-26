package com.csiprofessional.springcourse.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

    @Value("${password.for.test}")
    private String password;

    @GetMapping()
    public String version() {
        return password;
    }
}
