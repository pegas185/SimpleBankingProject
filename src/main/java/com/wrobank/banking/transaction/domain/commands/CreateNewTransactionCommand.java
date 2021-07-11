package com.wrobank.banking.transaction.domain.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
public class CreateNewTransactionCommand {
    private Integer accountId;
    private BigDecimal amount;

}
