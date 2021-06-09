package com.example.springtutorial.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @Autowired
    private AppConfiguration appConfiguration;

    @GetMapping("/welcome")
    public String hello() {
        return welcomeMessage;
    }

    @GetMapping("/app-conf")
    public AppConfiguration appConfigurationTypesafeAndDynamic() {
        return appConfiguration;
    }
}
