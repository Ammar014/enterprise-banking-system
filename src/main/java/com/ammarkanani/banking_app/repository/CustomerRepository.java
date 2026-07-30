package com.ammarkanani.banking_app.repository;

import com.ammarkanani.banking_app.dto.response.CustomerResponseDTO;
import com.ammarkanani.banking_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByCnic(String cnic);


}
