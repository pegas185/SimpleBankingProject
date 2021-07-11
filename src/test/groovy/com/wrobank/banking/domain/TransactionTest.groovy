package com.wrobank.banking.domain

import com.wrobank.banking.SimpleBankingProjectApplication
import com.wrobank.banking.transaction.domain.Transaction
import com.wrobank.banking.transaction.domain.commands.CreateNewTransactionCommand
import com.wrobank.banking.transaction.repositories.TransactionRepository
import com.wrobank.banking.transaction.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import java.lang.Void as Should

import static com.wrobank.banking.transaction.domain.TransactionType.INBOUND
import static com.wrobank.banking.transaction.domain.TransactionType.OUTBOUND

@SpringBootTest(classes = SimpleBankingProjectApplication)
@DirtiesContext
class TransactionTest extends Specification {

    @Autowired
    TransactionRepository transactionRepository
    @Autowired
    TransactionService transactionService

    Should "provide transactions by an ID of a valid account"() {
        given: "As a transaction viewer I have the valid accountId"
        def accountId = 100
        def amount = 10.0
        Transaction transaction = transactionRepository.save(provideTestTransaction(accountId, amount))

        when: "I query transactions with the valid accountId"
        List<Transaction> result = transactionService.findAllTransactionFor(accountId)

        then: "I should receive transactions associated with the accountId"
        result.contains(transaction)

        cleanup:
        transactionRepository.deleteById(transaction.id)
    }

    Should "get an empty list of transactions by an ID of a missing account"() {
        given: "As a transaction viewer I have the invalid accountId"
        def accountId = 100
        def amount = 10.0
        Transaction transaction = transactionRepository.save(provideTestTransaction(accountId, amount))
        def missingID = 2

        when: "I query transactions with the invalid accountId"
        List<Transaction> result = transactionService.findAllTransactionFor(missingID)

        then: "I should get invalid argument exception"
        result.isEmpty()

        cleanup:
        transactionRepository.deleteById(transaction.id)
    }

    Should "have a new transaction created for a valid account ID and an available credit #credit"() {
        given: "As a transaction manager I have the valid accountId and the available credit"
        def accountId = 102
        def testCommand = provideTestNewTransactionCommand(accountId, credit)

        when: "I command to create a new transaction for account"
        transactionService.processNewTransactionCommand(testCommand)
        def results = transactionRepository.findAllByAccountId(accountId)

        then: "a new transaction should be created for the provided credit and the account"
        !results.isEmpty()
        results[0].accountId == 102
        results[0].transactionType == transactionType
        results[0].amount == amount

        cleanup:
        transactionRepository.findAllByAccountId(accountId).each(transactionRepository.&delete)

        where:
        credit | transactionType | amount
        5      | INBOUND         | 5
        -5     | OUTBOUND        | 5
    }

    private Transaction provideTestTransaction(accountId = 1, BigDecimal amount = 10) {
        Transaction.builder()
                .accountId(accountId)
                .amount(Transaction.getAbsoluteAmount(amount))
                .transactionType(Transaction.getTransactionType(amount))
                .build()
    }

    private CreateNewTransactionCommand provideTestNewTransactionCommand(accountId = 1, BigDecimal amount = 10) {
        CreateNewTransactionCommand.builder()
                .accountId(accountId)
                .amount(amount)
                .build()
    }

}
