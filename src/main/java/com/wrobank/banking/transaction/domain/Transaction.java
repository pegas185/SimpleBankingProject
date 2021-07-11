package com.wrobank.banking.transaction.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Transaction {
    @Id
    private Integer id;
    private Integer accountId;
    private Short transactionType;
    private BigDecimal amount;

    public static short getTransactionType(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) < 0 ? TransactionType.OUTBOUND : TransactionType.INBOUND;
    }

    public static BigDecimal getAbsoluteAmount(BigDecimal amount) {
        return amount.abs();
    }
}
