package com.ammarkanani.banking_app.service;

import com.ammarkanani.banking_app.dto.request.CustomerRequestDTO;
import com.ammarkanani.banking_app.dto.response.AccountResponseDTO;
import com.ammarkanani.banking_app.dto.response.CustomerResponseDTO;
import com.ammarkanani.banking_app.entity.Customer;
import com.ammarkanani.banking_app.exception.DuplicateResourceException;
import com.ammarkanani.banking_app.exception.ResourceNotFoundException;
import com.ammarkanani.banking_app.mapper.AccountMapper;
import com.ammarkanani.banking_app.mapper.CustomerMapper;
import com.ammarkanani.banking_app.repository.AccountRepository;
import com.ammarkanani.banking_app.repository.CustomerRepository;

import java.time.LocalDateTime;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository,
                            CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {

        if (customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Email is already registered.");
        }

        if (customerRepository.existsByPhone(requestDTO.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number is already registered.");
        }

        if (customerRepository.existsByCnic(requestDTO.getCnic())) {
            throw new DuplicateResourceException("CNIC is already registered.");
        }

        Customer customer = new Customer();

        customer.setFirstName(requestDTO.getFirstName());

        customer.setLastName(requestDTO.getLastName());

        customer.setEmail(requestDTO.getEmail());

        customer.setPasswordHash(requestDTO.getPassword());

        customer.setPhone(requestDTO.getPhoneNumber());

        customer.setCnic(requestDTO.getCnic());

        customer.setStatus("ACTIVE");

        // customer.setCreatedAt(LocalDateTime.now());

        // customer.setUpdatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponseDTO response = new CustomerResponseDTO();

        response.setCustomerId(savedCustomer.getCustomerId());
        response.setFirstName(savedCustomer.getFirstName());
        response.setLastName(savedCustomer.getLastName());
        response.setEmail(savedCustomer.getEmail());

        return response;
    }

    public CustomerResponseDTO getCustomerById(Long id) {

        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: " + id));

        CustomerResponseDTO responseDTO = new CustomerResponseDTO();

        responseDTO.setCustomerId(customer.getCustomerId());
        responseDTO.setFirstName(customer.getFirstName());
        responseDTO.setLastName(customer.getLastName());
        responseDTO.setEmail(customer.getEmail());

        return responseDTO;
    }

    public List<CustomerResponseDTO> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> {

                    CustomerResponseDTO dto = new CustomerResponseDTO();

                    dto.setCustomerId(customer.getCustomerId());
                    dto.setFirstName(customer.getFirstName());
                    dto.setLastName(customer.getLastName());
                    dto.setEmail(customer.getEmail());

                    return dto;

                })
                .toList();

    }

    public CustomerResponseDTO updateCustomer(Long id,
            CustomerRequestDTO requestDTO) {

        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: " + id));

        if (!customer.getEmail().equals(requestDTO.getEmail())) {

            if (customerRepository.existsByEmail(requestDTO.getEmail())) {
                throw new DuplicateResourceException(
                        "Email already exists.");
            }

            customer.setEmail(requestDTO.getEmail());
        }

        customerMapper.updateEntity(requestDTO, customer);

        customerRepository.save(customer);

        CustomerResponseDTO response = customerMapper.toResponseDTO(customer);

        return response;
    }

    public void deleteCustomer(Long customerId) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: " + customerId));

        // customer.setStatus("INACTIVE");

        // customer.setUpdatedAt(LocalDateTime.now());

        customerRepository.save(customer);
    }



}
