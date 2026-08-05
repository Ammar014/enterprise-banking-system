package com.ammarkanani.banking_app.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ammarkanani.banking_app.enums.TransactionStatus;
import com.ammarkanani.banking_app.enums.TransactionType;
import com.ammarkanani.banking_app.enums.TransactionDirection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TransactionResponseDTO {

    private Long transactionId;

    private Long accountId;

    private BigDecimal amount;

    private TransactionType transactionType;

    private TransactionDirection direction;

    private TransactionStatus status;

    private String description;

    private BigDecimal currentBalance;

    private LocalDateTime transactionDate;



}
