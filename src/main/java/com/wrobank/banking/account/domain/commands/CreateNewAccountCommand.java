package com.wrobank.banking.account.domain.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
public class CreateNewAccountCommand {
    private Integer customerId;
    private BigDecimal amount;
}
