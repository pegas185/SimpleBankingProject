package com.wrobank.banking.account.services;

import com.wrobank.banking.account.domain.Account;
import com.wrobank.banking.account.domain.AccountDetails;
import com.wrobank.banking.customer.domain.Customer;
import com.wrobank.banking.customer.domain.CustomerDetails;
import com.wrobank.banking.customer.services.CustomerService;
import com.wrobank.banking.transaction.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountDetailsDbService implements AccountDetailsService {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @Override
    public CustomerDetails queryAccountDetailsFor(Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        List<Account> accounts = accountService.findAllAccountsFor(customerId);


        List<AccountDetails> accountDetails = accounts.stream()
                                                      .map(this::provideAccountDetailsFor)
                                                      .collect(Collectors.toList());

        return CustomerDetails.builder()
                              .customer(customer)
                              .accounts(accountDetails)
                              .build();
    }

    private AccountDetails provideAccountDetailsFor(Account account) {
        return AccountDetails.builder()
                             .id(account.getId())
                             .transactions(transactionService.findAllTransactionFor(account.getId()))
                             .build();
    }
}
