package com.wrobank.banking.account.controllers;

import com.wrobank.banking.account.domain.requests.AccountCreationResult;
import com.wrobank.banking.account.domain.requests.CreateNewAccountRequest;
import com.wrobank.banking.account.services.AccountDetailsService;
import com.wrobank.banking.account.services.AccountService;
import com.wrobank.banking.common.domain.CustomerWasNotFoundException;
import com.wrobank.banking.common.domain.UserWasNotFoundException;
import com.wrobank.banking.customer.domain.CustomerDetails;
import com.wrobank.banking.customer.services.CustomerService;
import com.wrobank.banking.user.domain.User;
import com.wrobank.banking.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/accounts/v1")
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountDetailsService accountDetailsService;
    private final AccountMapper accountMapper;
    private final UserService userService;
    private final CustomerService customerService;

    @PostMapping("/new")
    public AccountCreationResult processNewAccountCreation(@Valid @RequestBody CreateNewAccountRequest createNewAccountRequest) throws CustomerWasNotFoundException {

        validateCustomer(createNewAccountRequest);
        accountService.processNewAccountCommand(accountMapper.mapNewAccountRequestToCommand(createNewAccountRequest));
        return AccountCreationResult.builder()
                                    .message("Account was created")
                                    .build();
    }

    private void validateCustomer(CreateNewAccountRequest createNewAccountRequest) {
        customerService.findCustomerById(createNewAccountRequest.getCustomerId());
    }

    @GetMapping("/by-customer/{customerId}")
    public CustomerDetails queryAccountDetailsByCustomerID(@PathVariable @Valid @Positive @NotNull Integer customerId) throws CustomerWasNotFoundException {
        return accountDetailsService.queryAccountDetailsFor(customerId);
    }

    @GetMapping("/by-username/{username}")
    public CustomerDetails queryAccountDetailsByUsername(@PathVariable @Valid @NotBlank @NotNull String username) throws CustomerWasNotFoundException, UserWasNotFoundException {
        User user = userService.findUserByUsername(username);
        return queryAccountDetailsByCustomerID(user.getId());
    }

}
