package com.ammarkanani.banking_app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ammarkanani.banking_app.dto.request.AccountRequestDTO;
import com.ammarkanani.banking_app.dto.request.AccountUpdateRequestDTO;
import com.ammarkanani.banking_app.dto.request.CustomerRequestDTO;
import com.ammarkanani.banking_app.dto.response.AccountResponseDTO;
import com.ammarkanani.banking_app.dto.response.CustomerResponseDTO;
import com.ammarkanani.banking_app.entity.Account;
import com.ammarkanani.banking_app.entity.Customer;
import com.ammarkanani.banking_app.enums.AccountStatus;
import com.ammarkanani.banking_app.exception.ResourceNotFoundException;
import com.ammarkanani.banking_app.mapper.AccountMapper;
import com.ammarkanani.banking_app.repository.AccountRepository;
import com.ammarkanani.banking_app.repository.CustomerRepository;
import com.ammarkanani.banking_app.util.AccountNumberGenerator;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            AccountMapper accountMapper) {

        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
    }

    public AccountResponseDTO createAccount(Long customerId, AccountRequestDTO requestDTO) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: "
                                + customerId));

        Account account = accountMapper.toEntity(requestDTO);

        account.setCustomer(customer);
        account.setBalance(requestDTO.getInitialDeposit());
        account.setStatus(AccountStatus.ACTIVE);
        // account.setCreatedAt(LocalDateTime.now());
        // account.setUpdatedAt(LocalDateTime.now());

        String accountNumber;

        do {

            accountNumber = AccountNumberGenerator.generate();

        } while (accountRepository.existsByAccountNumber(accountNumber));

        account.setAccountNumber(accountNumber);

        accountRepository.save(account);

        return accountMapper.toResponseDTO(account);

    }

    public AccountResponseDTO getAccountById(long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found with ID: " + accountId));

        return accountMapper.toResponseDTO(account);
    }

    public List<AccountResponseDTO> getAccountsByCustomerId(long customerId) {

        List<Account> accounts = accountRepository.findByCustomerCustomerId(customerId);

        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: "
                                + customerId));

        return accounts.stream()
                .map(accountMapper::toResponseDTO)
                .toList();

    }

    public AccountResponseDTO updateAccount(Long accountId,
            AccountUpdateRequestDTO requestDTO) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        accountMapper.updateEntity(requestDTO, account);

        Account updatedAccount = accountRepository.save(account);

        return accountMapper.toResponseDTO(updatedAccount);

    }

    public void deleteAccount(Long accountId) {

        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found with ID: "
                                + accountId));

        account.setStatus(AccountStatus.CLOSED);

        accountRepository.save(account);

    }

}
