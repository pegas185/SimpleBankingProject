package com.wrobank.banking.transaction.services;

import com.wrobank.banking.transaction.domain.Transaction;
import com.wrobank.banking.transaction.domain.commands.CreateNewTransactionCommand;
import com.wrobank.banking.transaction.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDbService implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void processNewTransactionCommand(CreateNewTransactionCommand createNewTransactionCommand) {
        Transaction transaction = Transaction.builder()
                                             .accountId(createNewTransactionCommand.getAccountId())
                                             .transactionType(Transaction.getTransactionType(createNewTransactionCommand.getAmount()))
                                             .amount(Transaction.getAbsoluteAmount(createNewTransactionCommand.getAmount()))
                                             .build();
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllTransactionFor(Integer accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }
}
