package com.wrobank.banking.domain

import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Title

import java.lang.Void as Should

@Title("Customer domain should")
class CustomerTest extends Specification {

    @PendingFeature
    Should "provide customer Names by an ID of a valid customer"() {
        given: "As a customer viewer I have the valid customerId"

        when: "I query customer with the valid customerId"

        then: "I should receive names associated with the customerId"
        false
    }

    @PendingFeature
    Should "get an illegal argument exception by an ID of a missing customer"() {
        given: "As a customer viewer I have the invalid customerId"

        when: "I query customer with the invalid customerId"

        then: "I should get invalid argument exception"
        false
    }

}
