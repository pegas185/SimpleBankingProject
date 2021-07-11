package com.wrobank.banking.account.domain;

import com.wrobank.banking.transaction.domain.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class AccountDetails {
    private final Integer id;
    private final List<Transaction> transactions;
}
