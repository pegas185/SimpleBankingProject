package com.wrobank.banking.customer.services;

import com.wrobank.banking.customer.domain.Customer;

public interface CustomerService {
    Customer findCustomerById(Integer integer);
}