package com.ammarkanani.banking_app.dto.response;

import java.math.BigDecimal;

import com.ammarkanani.banking_app.enums.AccountStatus;
import com.ammarkanani.banking_app.enums.AccountType;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    private Long accountId;

    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private AccountStatus status;

}
