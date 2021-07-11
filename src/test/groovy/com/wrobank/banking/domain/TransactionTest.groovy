package com.wrobank.banking.domain

import spock.lang.PendingFeature
import spock.lang.Specification

import java.lang.Void as Should

import static com.wrobank.banking.domain.TransactionType.INBOUND
import static com.wrobank.banking.domain.TransactionType.OUTBOUND

class TransactionTest extends Specification {

    @PendingFeature
    Should "provide transactions by an ID of a valid account"() {
        given: "As a transaction viewer I have the valid accountId"

        when: "I query transactions with the valid accountId"

        then: "I should receive transactions associated with the accountId"
        false
    }

    @PendingFeature
    Should "get an illegal argument exception by an ID of a missing account"() {
        given: "As a transaction viewer I have the invalid accountId"

        when: "I query transactions with the invalid accountId"

        then: "I should get invalid argument exception"
        false
    }

    @PendingFeature
    Should "have a new transaction created for a valid account ID and an available credit #credit"() {
        given: "As a transaction manager I have the valid accountId and the available credit"

        when: "I command to create a new transaction for account"

        then: "a new transaction should be created for the provided credit and the account"
        false

        where:
        credit | transactionType | amount
        5      | INBOUND         | 5
        -5     | OUTBOUND        | 5
    }

    @PendingFeature
    Should "get an illegal argument exception for an invalid account ID and an available credit #credit"() {
        given: "As a transaction manager I have the invalid accountId and the available credit"

        when: "I command to create a new transaction for account"

        then: "I should get invalid argument exception"
        false

        where:
        credit | transactionType | amount
        5      | INBOUND         | 5
        -5     | OUTBOUND        | 5
    }

}
