package com.wrobank.banking.customer.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String surname;
}
