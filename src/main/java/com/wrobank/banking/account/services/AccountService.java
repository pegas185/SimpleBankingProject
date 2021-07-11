package com.wrobank.banking.account.services;

import com.wrobank.banking.account.domain.Account;
import com.wrobank.banking.account.domain.commands.CreateNewAccountCommand;

import java.util.List;

public interface AccountService {

    void processNewAccountCommand(CreateNewAccountCommand mapNewAccountRequestToCommand);

    List<Account> findAllAccountsFor(Integer customerID);
}
