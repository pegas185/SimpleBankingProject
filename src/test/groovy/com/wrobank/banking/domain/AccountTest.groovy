package com.wrobank.banking.domain

import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Title

import java.lang.Void as Should

import static com.wrobank.banking.domain.TransactionType.INBOUND
import static com.wrobank.banking.domain.TransactionType.OUTBOUND

@Title("Customer domain should")
class AccountTest extends Specification {

    @PendingFeature
    Should "provide a customer ID by an ID of a valid account"() {
        given: "As a account viewer I have the valid accountId"

        when: "I query account with the valid accountId"

        then: "I should receive a customer ID associated with the accountId"
        false
    }

    @PendingFeature
    Should "get an illegal argument exception by an ID of a missing account"() {
        given: "As a account viewer I have the invalid accountId"

        when: "I query account with the invalid accountId"

        then: "I should get an invalid argument exception"
        false
    }

    @PendingFeature
    Should "have a new account created for a valid customer ID and no credit available"() {
        given: "As a account manager I have the valid customerId"

        when: "I command to create a new account for customer"

        then: "the new account should be created with an empty list of transactions"
        false
    }

    @PendingFeature
    Should "get an illegal argument exception for an invalid customer ID and no credit available"() {
        given: "As a account manager I have the invalid customerId"

        when: "I command to create a new account for customer"

        then: "I should get an invalid argument exception"
        false
    }

    @PendingFeature
    Should "have a new account created for a valid customer ID and an available credit: #credit"() {
        given: "As a account manager I have the valid customerId and the available credit"

        when: "I command to create a new account for customer"

        then: "the new account should be created with a transaction for the available credit"
        false

        where:
        credit | transactionType | amount
        5      | INBOUND         | 5
        -5     | OUTBOUND        | 5
    }

}
