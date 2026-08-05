package com.ammarkanani.banking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.*;

import com.ammarkanani.banking_app.dto.request.AmountRequestDTO;
import com.ammarkanani.banking_app.dto.request.TransferRequestDTO;
import com.ammarkanani.banking_app.dto.response.TransactionResponseDTO;
import com.ammarkanani.banking_app.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "account.accountId", target = "accountId")
    @Mapping(source = "account.balance", target = "currentBalance")
    TransactionResponseDTO toResponseDTO(Transaction transaction);

    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "transactionType", ignore = true)
    @Mapping(target = "direction", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    Transaction toEntity(AmountRequestDTO dto);

    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "transactionType", ignore = true)
    @Mapping(target = "direction", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    Transaction toEntity(TransferRequestDTO dto);

}
