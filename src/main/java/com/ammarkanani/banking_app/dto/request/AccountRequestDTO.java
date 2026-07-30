package com.ammarkanani.banking_app.dto.request;

import java.math.BigDecimal;

import com.ammarkanani.banking_app.enums.AccountType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

    @NotNull(message = "Account type is required.")
    private AccountType accountType;

    @NotNull(message = "Initial deposit is required.")
    @DecimalMin(value = "0.00", message = "Initial deposit cannot be negative.")
    private BigDecimal initialDeposit;

}
