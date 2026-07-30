package com.ammarkanani.banking_app.service;

import org.springframework.stereotype.Service;

import com.ammarkanani.banking_app.dto.ApplicationStatusResponse;

@Service
public class HealthService {
    public ApplicationStatusResponse getApplicationStatusResponse(){
        return new ApplicationStatusResponse("Banking Application",
        "UP",
        "1.0.0");
    }
}
