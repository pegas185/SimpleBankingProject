package com.wrobank.banking.account.domain.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AccountCreationResult {
    private String message;
}
