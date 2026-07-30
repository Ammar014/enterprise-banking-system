package com.ammarkanani.banking_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ammarkanani.banking_app.dto.request.CustomerRequestDTO;
import com.ammarkanani.banking_app.dto.response.ApiResponse;
import com.ammarkanani.banking_app.dto.response.CustomerResponseDTO;
import com.ammarkanani.banking_app.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> createCustomer(
            @Valid @RequestBody CustomerRequestDTO requestDTO) {

        CustomerResponseDTO responseDTO = customerService.createCustomer(requestDTO);

        ApiResponse<CustomerResponseDTO> response = new ApiResponse<>(
                true,
                "Customer created successfully.",
                responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerById(
            @PathVariable Long id) {

        CustomerResponseDTO responseDTO = customerService.getCustomerById(id);

        ApiResponse<CustomerResponseDTO> response = new ApiResponse<>(
                true,
                "Customer fetched successfully.",
                responseDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> getAllCustomers() {

        List<CustomerResponseDTO> responseAll = customerService.getAllCustomers();

        ApiResponse<List<CustomerResponseDTO>> response = new ApiResponse<>(
                true,
                "Customers fetched successfully.",
                responseAll);

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> updateCustomer(
            @PathVariable Long id, @Valid @RequestBody CustomerRequestDTO requestDTO) {

        CustomerResponseDTO response = customerService.updateCustomer(id, requestDTO);

        ApiResponse<CustomerResponseDTO> apiResponse = new ApiResponse<>(
                true,
                "Customer updated successfully",
                response);

        return ResponseEntity.ok(apiResponse);

    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        ApiResponse<Void> response = new ApiResponse<>(

                true,

                "Customer deactivated successfully.",

                null);

        return ResponseEntity.ok(response);
    }

}
