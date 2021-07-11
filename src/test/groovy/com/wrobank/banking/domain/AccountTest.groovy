package com.wrobank.banking.domain

import com.wrobank.banking.SimpleBankingProjectApplication
import com.wrobank.banking.account.domain.Account
import com.wrobank.banking.account.domain.commands.CreateNewAccountCommand
import com.wrobank.banking.account.repositories.AccountRepository
import com.wrobank.banking.account.services.AccountService
import com.wrobank.banking.transaction.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import java.lang.Void as Should

import static com.wrobank.banking.transaction.domain.TransactionType.INBOUND
import static com.wrobank.banking.transaction.domain.TransactionType.OUTBOUND

@SpringBootTest(classes = SimpleBankingProjectApplication)
@DirtiesContext
class AccountTest extends Specification {

    @Autowired
    private AccountRepository accountRepository
    @Autowired
    private TransactionRepository transactionRepository
    @Autowired
    private AccountService accountService

    Should "provide an account by an ID of a valid customer"() {
        given: "As a account viewer I have the valid customer id"
        def customerId = 100
        Account account = accountRepository.save(provideTestAccount(customerId))

        when: "I query account with the valid customer id"
        List<Account> result = accountService.findAllAccountsFor(customerId)

        then: "I should receive a list of accounts associated with the customer id"
        !result.isEmpty()
        result[0].customerId == customerId
        result[0].id != null

        cleanup:
        accountRepository.deleteById(account.id)
    }

    Should "get an empty list by an ID of a missing customer"() {
        given: "As a account viewer I have the invalid customer id"
        def customerId = 100
        def invalidCustomerId = 102
        Account account = accountRepository.save(provideTestAccount(customerId))


        when: "I query account with the invalid customer id"
        List<Account> result = accountService.findAllAccountsFor(invalidCustomerId)

        then: "I should get an empty list of accounts"
        result.isEmpty()

        cleanup:
        accountRepository.deleteById(account.id)
    }

    Should "have a new account created for a valid customer ID and no credit available"() {
        given: "As a account manager I have the valid customerId"
        def customerId = 103
        CreateNewAccountCommand command = provideTestNewAccountCommand(customerId)

        when: "I command to create a new account for customer"
        accountService.processNewAccountCommand(command)
        List<Account> result = accountService.findAllAccountsFor(customerId)

        then: "the new account should be created with an empty list of transactions"
        !result.isEmpty()
        result[0].customerId == customerId
        result[0].id != null
        transactionRepository.findAllByAccountId(result[0].id).isEmpty()

        cleanup:
        accountRepository.deleteById(result[0].id)
    }

    Should "have a new account created for a valid customer ID and an available credit: #credit"() {
        given: "As a account manager I have the valid customerId and the available credit"
        def customerId = 103
        CreateNewAccountCommand command = provideTestNewAccountCommand(customerId, credit)

        when: "I command to create a new account for customer"
        accountService.processNewAccountCommand(command)
        List<Account> result = accountService.findAllAccountsFor(customerId)

        then: "the new account should be created with a transaction for the available credit"
        !result.isEmpty()
        result[0].customerId == customerId
        result[0].id != null
        def transaction = transactionRepository.findAllByAccountId(result[0].id)[0]
        transaction.amount == amount
        transaction.transactionType == transactionType

        cleanup:
        accountRepository.deleteById(result[0].id)
        transactionRepository.findAllByAccountId(result[0].id).each(transactionRepository.&delete)

        where:
        credit | transactionType | amount
        5      | INBOUND         | 5
        -5     | OUTBOUND        | 5
    }

    Account provideTestAccount(Integer customerId) {
        Account.builder()
                .customerId(customerId)
                .build()
    }

    CreateNewAccountCommand provideTestNewAccountCommand(Integer customerId, BigDecimal amount = 0) {
        CreateNewAccountCommand.builder()
                .customerId(customerId)
                .amount(amount)
                .build()
    }
}
