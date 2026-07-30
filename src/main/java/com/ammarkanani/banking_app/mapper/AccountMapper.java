package com.ammarkanani.banking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ammarkanani.banking_app.dto.request.AccountRequestDTO;
import com.ammarkanani.banking_app.dto.response.AccountResponseDTO;
import com.ammarkanani.banking_app.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Account toEntity(AccountRequestDTO dto);

    AccountResponseDTO toResponseDTO(Account account);

}
