package com.wrobank.banking.domain

import com.wrobank.banking.SimpleBankingProjectApplication
import com.wrobank.banking.common.domain.UserWasNotFoundException
import com.wrobank.banking.user.domain.User
import com.wrobank.banking.user.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import java.lang.Void as Should

@SpringBootTest(classes = SimpleBankingProjectApplication)
@DirtiesContext
class UserTest extends Specification {

    @Autowired
    UserService userService

    Should "find user by valid username"() {
        given: "I'm a user viewer and I provide the valid username"
        String valid_username = 'user1'

        when: "I query users by the username"
        User user = userService.findUserByUsername(valid_username)


        then: "I should have a valid user"
        user.id != null
        user.username != null
    }

    Should "I should not see any users by an invalid username"() {
        given: "I'm a user viewer and I provide the invalid username"
        String invalid_username = 'invalid'

        when: "I query users by the username"
        User user = userService.findUserByUsername(invalid_username)

        then: "I should get user not found exception"
        thrown(UserWasNotFoundException)
    }
}
