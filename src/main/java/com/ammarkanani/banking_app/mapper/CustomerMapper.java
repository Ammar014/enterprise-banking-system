package com.ammarkanani.banking_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.*;

import com.ammarkanani.banking_app.dto.request.CustomerRequestDTO;
import com.ammarkanani.banking_app.dto.response.CustomerResponseDTO;
import com.ammarkanani.banking_app.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "password", target = "passwordHash")
    Customer toEntity(CustomerRequestDTO dto);

    CustomerResponseDTO toResponseDTO(Customer customer);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "password", target = "passwordHash")
    void updateEntity(CustomerRequestDTO dto,
                      @MappingTarget Customer customer);

}
