package com.wrobank.banking.transaction.services;

import com.wrobank.banking.transaction.domain.Transaction;
import com.wrobank.banking.transaction.domain.commands.CreateNewTransactionCommand;

import java.util.List;

public interface TransactionService {
    void processNewTransactionCommand(CreateNewTransactionCommand createNewTransactionCommand);

    List<Transaction> findAllTransactionFor(Integer accountId);
}
