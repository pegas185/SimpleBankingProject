package com.wrobank.banking.domain

import spock.lang.PendingFeature
import spock.lang.Specification

import java.lang.Void as Should

class UserTest extends Specification {

    @PendingFeature
    Should "authenticate user by valid credentials"() {
        given: "I'm a valid user and I provide the valid credentials"

        when: "I login to the system"

        then: "I should be granted with access to the system"
        false
    }

    @PendingFeature
    Should "not authenticate user by invalid credentials"() {
        given: "I'm a user and I provide the invalid credentials"

        when: "I login to the system"

        then: "I should not be able to login to the system and have an authentication error"
        false
    }
}
