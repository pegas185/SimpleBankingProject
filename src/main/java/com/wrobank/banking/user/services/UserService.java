package com.wrobank.banking.user.services;

import com.wrobank.banking.user.domain.User;

public interface UserService {
    User findUserByUsername(String username);
}
