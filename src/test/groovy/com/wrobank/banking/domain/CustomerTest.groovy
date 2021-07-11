package com.wrobank.banking.domain

import com.wrobank.banking.SimpleBankingProjectApplication
import com.wrobank.banking.customer.domain.Customer
import com.wrobank.banking.customer.services.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import java.lang.Void as Should

@SpringBootTest(classes = SimpleBankingProjectApplication)
@DirtiesContext
class CustomerTest extends Specification {

    @Autowired
    CustomerService customerService;

    Should "provide customer Names by an ID of a valid customer"() {
        given: "As a customer viewer I have the valid customerId"
        Integer validCustomerId = 2

        when: "I query customer with the valid customerId"
        Customer result = customerService.findCustomerById(validCustomerId)

        then: "I should receive names associated with the customerId"
        result.id == 2
        result.name == 'Mortimer'
        result.surname == 'Smith'
    }

    Should "get an illegal argument exception by an ID of a missing customer"() {
        given: "As a customer viewer I have the invalid customerId"
        Integer validCustomerId = 1000

        when: "I query customer with the invalid customerId"
        Customer result = customerService.findCustomerById(validCustomerId)

        then: "I should get invalid argument exception"
        thrown(IllegalArgumentException)
    }

}
