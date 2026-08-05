package com.ammarkanani.banking_app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ammarkanani.banking_app.dto.request.AmountRequestDTO;
import com.ammarkanani.banking_app.dto.request.TransferRequestDTO;
import com.ammarkanani.banking_app.dto.response.TransactionResponseDTO;
import com.ammarkanani.banking_app.entity.Account;
import com.ammarkanani.banking_app.entity.Transaction;
import com.ammarkanani.banking_app.enums.AccountStatus;
import com.ammarkanani.banking_app.enums.TransactionDirection;
import com.ammarkanani.banking_app.enums.TransactionStatus;
import com.ammarkanani.banking_app.enums.TransactionType;
import com.ammarkanani.banking_app.exception.BusinessException;
import com.ammarkanani.banking_app.exception.ResourceNotFoundException;
import com.ammarkanani.banking_app.mapper.TransactionMapper;
import com.ammarkanani.banking_app.repository.AccountRepository;
import com.ammarkanani.banking_app.repository.TransactionRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionService(

            AccountRepository accountRepository,

            TransactionRepository transactionRepository,

            TransactionMapper transactionMapper) {

        this.accountRepository = accountRepository;

        this.transactionRepository = transactionRepository;

        this.transactionMapper = transactionMapper;

    }

    @Transactional
    public TransactionResponseDTO deposit(Long accountId, AmountRequestDTO requestDTO) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found."));

        if (account.getStatus() != AccountStatus.ACTIVE) {

            throw new BusinessException("Only active accounts can receive deposits.");

        }

        account.deposit(requestDTO.getAmount());

        Transaction transaction = transactionMapper.toEntity(requestDTO);

        transaction.setAccount(account);
        // transaction.setAmount(requestDTO.getAmount());
        transaction.setDirection(TransactionDirection.CREDIT);
        // transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setDescription(requestDTO.getDescription());
        // transaction.setTransactionDate(LocalDateTime.now());

        // accountRepository.save(account);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toResponseDTO(savedTransaction);

    }

    @Transactional
    public TransactionResponseDTO withdraw(Long accountId, AmountRequestDTO requestDTO) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found."));

        if (account.getStatus() != AccountStatus.ACTIVE) {

            throw new BusinessException("Account is inactive.");

        }

        if (account.getBalance().compareTo(requestDTO.getAmount()) < 0) {

            throw new BusinessException("Insufficient balance.");

        }

        account.withdraw(requestDTO.getAmount());

        Transaction transaction = transactionMapper.toEntity(requestDTO);

        transaction.setAccount(account);

        transaction.setTransactionType(TransactionType.WITHDRAW);

        transaction.setDirection(TransactionDirection.DEBIT);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toResponseDTO(savedTransaction);

    }

    @Transactional
    public TransactionResponseDTO transfer(Long sourceAccountId, TransferRequestDTO requestDTO) {

        Account sourceAccount = accountRepository.findById(sourceAccountId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                "Source account not found."));

        Account destinationAccount = accountRepository.findByAccountNumber(requestDTO.getDestinationAccountNumber())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                    "Destination account not found."));

        if (sourceAccount.getAccountId().equals(destinationAccount.getAccountId())) {
                            throw new BusinessException("Cannot transfer to the same account.");

        }

        if (sourceAccount.getStatus() != AccountStatus.ACTIVE) 
        {
            throw new BusinessException(
            "Source account is inactive.");

        }





        if (destinationAccount.getStatus() != AccountStatus.ACTIVE) {

            throw new BusinessException(

                    "Destination account is inactive.");

        }

        if (sourceAccount.getBalance()

                .compareTo(requestDTO.getAmount()) < 0) {

            throw new BusinessException(

                    "Insufficient balance.");

        }

        sourceAccount.withdraw(

                requestDTO.getAmount());

        destinationAccount.deposit(

                requestDTO.getAmount());

        Transaction debitTransaction =

                transactionMapper.toEntity(requestDTO);

        debitTransaction.setAccount(sourceAccount);

        debitTransaction.setTransactionType(

                TransactionType.TRANSFER);

        debitTransaction.setDirection(

                TransactionDirection.DEBIT);

        Transaction creditTransaction =

                transactionMapper.toEntity(requestDTO);

        creditTransaction.setAccount(destinationAccount);

        creditTransaction.setTransactionType(

                TransactionType.TRANSFER);

        creditTransaction.setDirection(

                TransactionDirection.CREDIT);

        Transaction savedDebit =

                transactionRepository.save(debitTransaction);

        transactionRepository.save(creditTransaction);

        return transactionMapper.toResponseDTO(savedDebit);

    }

    public Page<TransactionResponseDTO> getTransactionHistory(
        Long accountId, int page, int size, String sortBy, String sortDirection){

        Account account = accountRepository.findById(accountId)
                          .orElseThrow(() -> new ResourceNotFoundException(
                                            "Account not found."));

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Transaction> transactions = 
                        transactionRepository.findByAccountAccountId(accountId, pageable);

        return transactions.map(transactionMapper::toResponseDTO);

        

    }

}
