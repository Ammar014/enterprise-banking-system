package com.ammarkanani.banking_app.dto.request;

import com.ammarkanani.banking_app.enums.AccountStatus;
import com.ammarkanani.banking_app.enums.AccountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateRequestDTO {

    @NotNull
    private AccountType accountType;

    @NotNull
    private AccountStatus status;

}
