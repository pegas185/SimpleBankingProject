package com.wrobank.banking.account.domain.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateNewAccountRequest {
    @NotNull
    @Positive
    private Integer customerId;
    @NotNull
    private BigDecimal amount;
}
