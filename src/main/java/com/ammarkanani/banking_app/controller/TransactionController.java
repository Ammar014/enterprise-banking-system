package com.ammarkanani.banking_app.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ammarkanani.banking_app.dto.request.AmountRequestDTO;
import com.ammarkanani.banking_app.dto.request.TransferRequestDTO;
import com.ammarkanani.banking_app.dto.response.ApiResponse;
import com.ammarkanani.banking_app.dto.response.TransactionResponseDTO;
import com.ammarkanani.banking_app.service.TransactionService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<ApiResponse<TransactionResponseDTO>> deposit(
            @PathVariable Long accountId, @Valid @RequestBody AmountRequestDTO requestDTO) {

        TransactionResponseDTO response = transactionService.deposit(accountId, requestDTO);

        ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<>(

                true,

                "Amount deposited successfully.",

                response);

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<ApiResponse<TransactionResponseDTO>> withdraw(

            @PathVariable Long accountId,

            @Valid @RequestBody AmountRequestDTO requestDTO) {

        TransactionResponseDTO response = transactionService.withdraw(accountId, requestDTO);

        ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<>(

                true,

                "Amount withdrawn successfully.",

                response);

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/{sourceAccountId}/transfer")
    public ResponseEntity<ApiResponse<TransactionResponseDTO>> transfer(
            @PathVariable Long sourceAccountId, @Valid @RequestBody TransferRequestDTO requestDTO) {

        TransactionResponseDTO response = transactionService.transfer(
                sourceAccountId, requestDTO);

        ApiResponse<TransactionResponseDTO> apiResponse = new ApiResponse<>(

                true,
                "Transfer completed successfully.",
                response);

        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<ApiResponse<Page<TransactionResponseDTO>>> getHistory
    (@PathVariable Long accountId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "transactionDate") String sortBy, @RequestParam(defaultValue = "desc") String sortDirection) {

        Page<TransactionResponseDTO> response = transactionService.getTransactionHistory(
            accountId,page,size,sortBy,sortDirection);

        ApiResponse<Page<TransactionResponseDTO>> apiResponse = new ApiResponse<>(

                        true,

                        "Transaction history fetched successfully.",

                        response);

        return ResponseEntity.ok(apiResponse);

    }

}
