package com.wrobank.banking.account.controllers;

import com.wrobank.banking.account.domain.commands.CreateNewAccountCommand;
import com.wrobank.banking.account.domain.requests.CreateNewAccountRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public CreateNewAccountCommand mapNewAccountRequestToCommand(CreateNewAccountRequest request) {
        return CreateNewAccountCommand.builder()
                                      .customerId(request.getCustomerId())
                                      .amount(request.getAmount())
                                      .build();
    }

}
