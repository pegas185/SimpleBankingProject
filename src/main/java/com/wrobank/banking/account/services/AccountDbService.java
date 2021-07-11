package com.wrobank.banking.account.services;

import com.wrobank.banking.account.domain.Account;
import com.wrobank.banking.account.domain.commands.CreateNewAccountCommand;
import com.wrobank.banking.account.repositories.AccountRepository;
import com.wrobank.banking.transaction.domain.commands.CreateNewTransactionCommand;
import com.wrobank.banking.transaction.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDbService implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Transactional
    public void processNewAccountCommand(CreateNewAccountCommand mapNewAccountRequestToCommand) {

        Account account = Account.builder()
                                 .customerId(mapNewAccountRequestToCommand.getCustomerId())
                                 .build();

        account = accountRepository.save(account);

        BigDecimal amount = mapNewAccountRequestToCommand.getAmount();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        CreateNewTransactionCommand createNewTransactionCommand =
                CreateNewTransactionCommand.builder()
                                           .accountId(account.getId())
                                           .amount(mapNewAccountRequestToCommand.getAmount())
                                           .build();

        transactionService.processNewTransactionCommand(createNewTransactionCommand);
    }

    @Override
    public List<Account> findAllAccountsFor(Integer customerID) {
        return accountRepository.findAllByCustomerId(customerID);
    }

}
