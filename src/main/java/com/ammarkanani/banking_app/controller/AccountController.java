package com.ammarkanani.banking_app.controller;

import org.springframework.http.ResponseEntity;

import com.ammarkanani.banking_app.dto.request.AccountRequestDTO;
import com.ammarkanani.banking_app.dto.request.AccountUpdateRequestDTO;
import com.ammarkanani.banking_app.dto.request.CustomerRequestDTO;
import com.ammarkanani.banking_app.dto.response.AccountResponseDTO;
import com.ammarkanani.banking_app.dto.response.ApiResponse;
import com.ammarkanani.banking_app.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

                ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<>(
                                true,
                                "Account created successfully.",
                                response);

                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

        }

        @GetMapping("/accounts/{accountId}")
        public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountById(
                        @PathVariable long accountId) {

                AccountResponseDTO response = accountService.getAccountById(accountId);

                ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<>(
                                true,
                                "Account fetched successfully",
                                response);

                return ResponseEntity.ok(apiResponse);

        }

        @GetMapping("/customers/{customerId}/accounts")
        public ResponseEntity<ApiResponse<List<AccountResponseDTO>>> getAccountsByCustomerId(
                        @PathVariable Long customerId) {

                List<AccountResponseDTO> response = accountService.getAccountsByCustomerId(customerId);

                ApiResponse<List<AccountResponseDTO>> apiResponse = new ApiResponse<>(
                                true,
                                "Accounts retrieved successfully.",
                                response);

                return ResponseEntity.ok(apiResponse);
        }

        @PutMapping("/accounts/{accountId}")
        public ResponseEntity<ApiResponse<AccountResponseDTO>> updateAccount(
                        @PathVariable long accountId,
                        @Valid @RequestBody AccountUpdateRequestDTO requestDTO) {

                AccountResponseDTO response = accountService.updateAccount(accountId, requestDTO);

                ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<>(
                                true,
                                "Account updated successfully",
                                response);

                return ResponseEntity.ok(apiResponse);
        }

        @DeleteMapping("/accounts/{accountId}")
        public ResponseEntity<ApiResponse<Void>> deleteAccount(
                        @PathVariable Long accountId) {

                accountService.deleteAccount(accountId);

                ApiResponse<Void> response = new ApiResponse<>(
                                true,
                                "Account closed successfully.",
                                null);

                return ResponseEntity.ok(response);
        }

}
