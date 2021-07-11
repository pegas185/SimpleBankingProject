package com.wrobank.banking.user.controllers;

import com.wrobank.banking.user.domain.User;
import com.wrobank.banking.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @RequestMapping("/user")
    public User getUserDetails(Principal user) {
        return userService.findUserByName(user.getName());
    }
}
