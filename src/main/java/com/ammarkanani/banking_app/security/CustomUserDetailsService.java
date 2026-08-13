package com.ammarkanani.banking_app.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ammarkanani.banking_app.entity.Customer;
import com.ammarkanani.banking_app.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(
            CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;

    }

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("Customer not found"));

        return User.withUsername(customer.getEmail())
                    .password(customer.getPasswordHash())
                    .roles("CUSTOMER")
                    .build();

    }

}
