package com.ammarkanani.banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ammarkanani.banking_app.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(String accountNumber);

    List<Account> findByCustomerCustomerId(Long customerId);

    Optional<Account> findByAccountNumber(String accountNumber);

}
