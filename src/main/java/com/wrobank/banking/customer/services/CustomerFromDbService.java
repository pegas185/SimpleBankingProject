package com.wrobank.banking.customer.services;

import com.wrobank.banking.common.domain.CustomerWasNotFoundException;
import com.wrobank.banking.customer.domain.Customer;
import com.wrobank.banking.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFromDbService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                                 .orElseThrow(() -> new CustomerWasNotFoundException("Customer is not found: " + id));
    }
}
