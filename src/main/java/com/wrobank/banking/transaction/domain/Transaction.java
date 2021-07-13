package com.wrobank.banking.transaction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Builder
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id)
                && Objects.equals(accountId, that.accountId)
                && Objects.equals(transactionType, that.transactionType)
                && (amount != null ? amount.compareTo(that.amount) == 0 : that.amount == null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, transactionType, amount);
    }
}
