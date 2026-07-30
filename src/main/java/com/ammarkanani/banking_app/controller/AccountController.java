package com.ammarkanani.banking_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ammarkanani.banking_app.dto.request.AccountRequestDTO;
import com.ammarkanani.banking_app.dto.response.AccountResponseDTO;
import com.ammarkanani.banking_app.dto.response.ApiResponse;
import com.ammarkanani.banking_app.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> createAccount(
        @PathVariable Long customerId,
        @Valid @RequestBody AccountRequestDTO requestDTO) {

        AccountResponseDTO response = accountService.createAccount(
            customerId,
            requestDTO);

        ApiResponse<AccountResponseDTO> apiResponse =
        new ApiResponse<>(
                true,
                "Account created successfully.",
                response
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

}
