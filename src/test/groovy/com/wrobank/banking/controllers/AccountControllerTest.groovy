package com.wrobank.banking.controllers

import com.wrobank.banking.account.controllers.AccountController
import com.wrobank.banking.account.controllers.AccountMapper
import com.wrobank.banking.account.services.AccountDetailsService
import com.wrobank.banking.account.services.AccountService
import com.wrobank.banking.customer.services.CustomerService
import com.wrobank.banking.user.services.UserService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import java.lang.Void as Should

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest(AccountController)
@DirtiesContext
class AccountControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    AccountService accountService = Mock()
    @SpringBean
    AccountDetailsService accountDetailsService = Mock()
    @SpringBean
    AccountMapper accountMapper = Mock()
    @SpringBean
    UserService userService = Mock()
    @SpringBean
    CustomerService customerService = Mock()

    Should "query account details for by-customer endpoint"() {
        given:

        when:
        MvcResult result = mockMvc.perform(get("/accounts/v1/by-customer/1")).andReturn()

        then:
        result.response.status == HttpStatus.OK.value()
        result.response.contentAsString == ""
    }

    Should "validate account details for 'new' endpoint for customer #customerId and amount #amount"() {
        given:
        String content = "{\"customerId\": $customerId, \"amount\": $amount}";

        when:
        MvcResult result = mockMvc.perform(post("/accounts/v1/new")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON).content(content))
                .andReturn()

        then:
        result.response.status == status.value()
        result.response.contentAsString.contains(message)

        where:
        customerId | amount | status                 | message
        1          | null   | HttpStatus.BAD_REQUEST | "on field 'amount': rejected value [null]"
        null       | 1      | HttpStatus.BAD_REQUEST | "on field 'customerId': rejected value [null]"
        1          | 1      | HttpStatus.OK          | ""
    }

}
