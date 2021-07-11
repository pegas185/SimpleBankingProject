package com.wrobank.banking.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {
    private Long id;
    private String userName;
}
