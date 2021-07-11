package com.wrobank.banking.customer.domain;

import com.wrobank.banking.account.domain.AccountDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class CustomerDetails {
    private final Customer customer;
    private final List<AccountDetails> accounts;
}
