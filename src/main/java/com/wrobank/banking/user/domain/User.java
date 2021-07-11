package com.wrobank.banking.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class User {
    @Id
    private Integer id;
    private String username;
}
