package com.ammarkanani.banking_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ammarkanani.banking_app.BankingAppApplication;
import com.ammarkanani.banking_app.dto.ApplicationStatusResponse;
import com.ammarkanani.banking_app.service.HealthService;

import org.apache.tomcat.util.http.parser.Upgrade;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService){
        this.healthService = healthService;
    }
    
    @GetMapping("/health")
    public ApplicationStatusResponse health() {
        return healthService.getApplicationStatusResponse();
    }
    
}
