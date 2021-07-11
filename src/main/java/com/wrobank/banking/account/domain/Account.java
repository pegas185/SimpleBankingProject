package com.wrobank.banking.account.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Account {
    @Id
    private Integer id;
    private Integer customerId;
}
